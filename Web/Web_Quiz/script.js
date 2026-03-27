document.addEventListener("DOMContentLoaded", async () => {
    const loader = document.getElementById("loader");
    const questionsContainer = document.getElementById("questions-container");

    // Mostra loader
    loader.style.display = "block";

    try {
        // Chiama API con retry logic
        const domande = await getQuestionsWithRetry(10, 5); // max 5 tentativi
        hideLoader(loader);

        if (!domande || !domande.results || domande.results.length === 0) {
            showError(questionsContainer, "Nessuna domanda disponibile al momento. Riprova più tardi.");
            return;
        }

        displayQuestions(domande.results, questionsContainer);
    } catch (error) {
        hideLoader(loader);
        console.error("Errore nel caricamento delle domande:", error);
        showError(questionsContainer, "Errore nel caricamento delle domande. Riprova tra qualche secondo.");
    }
});

/**
 * Fetch domande con retry logic
 * Aspetta 5 secondi e riprova se status != 200 o response == undefined
 */
async function getQuestionsWithRetry(amount, maxRetries = 5) {
    const url = `https://opentdb.com/api.php?amount=${amount}`;
    let lastError = null;

    for (let attempt = 1; attempt <= maxRetries; attempt++) {
        try {
            console.log(`Tentativo ${attempt}/${maxRetries}: fetching ${url}`);

            const response = await fetch(url);

            // Controlla se la response è valida
            if (!response || response.status !== 200) {
                const status = response ? response.status : 'undefined';
                throw new Error(`HTTP ${status}`);
            }

            const data = await response.json();

            // Validazione dati
            if (!data || !data.results) {
                throw new Error("Response data invalid");
            }

            console.log(`Tentativo ${attempt}: success`);
            return data;

        } catch (error) {
            lastError = error;
            console.warn(`Tentativo ${attempt} fallito:`, error.message);

            // Se non è l'ultimo tentativo, aspetta 5 secondi
            if (attempt < maxRetries) {
                await sleep(5000);
            }
        }
    }

    // Tutti i tentativi falliti
    throw new Error(`Impossibile caricare le domande dopo ${maxRetries} tentativi. Ultimo errore: ${lastError.message}`);
}

/**
 * Funzione di utilità per delay
 */
function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}

/**
 * Mostra messaggio di errore nel container
 */
function showError(container, message) {
    container.innerHTML = `
        <div class="alert alert-danger" role="alert">
            <h4 class="alert-heading">Errore</h4>
            <p>${message}</p>
            <hr>
            <p class="mb-0">Per favore, ricarica la pagina o riprova più tardi.</p>
        </div>
    `;
}

/**
 * Hide loader element
 */
function hideLoader(loader) {
    loader.style.display = "none";
}

function displayQuestions(questions, container) {
    container.innerHTML = "";

    questions.forEach((question, question_id) => {
        const boxDomanda = document.createElement("div");
        boxDomanda.classList.add("box-domanda", "mb-3", "w-100", "w-md-50");
        boxDomanda.id = "box-domanda-" + question_id;

        const domanda = document.createElement("div");
        domanda.classList.add("domanda");
        let urlEncodedQuestion = question.question;
        domanda.innerHTML = decodeURIComponent(urlEncodedQuestion);

        const risposte = document.createElement("div");
        risposte.classList.add("risposte");

        let risposte_vals = [
            question.correct_answer,
            ...question.incorrect_answers
        ];
        // shuffle
        risposte_vals.sort(() => Math.random() - 0.5);

        risposte_vals.forEach((risposta, i) => {
            const risposta_dom = document.createElement("input");
            risposta_dom.type = "radio";
            risposta_dom.name = "risposta";
            risposta_dom.id = "risposta" + question_id + "_" + i;
            risposta_dom.value = risposta;

            const rispostaLabel = document.createElement("label");
            rispostaLabel.htmlFor = "risposta" + question_id + "_" + i;
            rispostaLabel.innerHTML = decodeURIComponent(risposta);
            risposte.appendChild(risposta_dom);
            risposte.appendChild(rispostaLabel);
        });

        boxDomanda.appendChild(domanda);
        boxDomanda.appendChild(risposte);

        const checkButton = document.createElement("button");
        checkButton.classList.add("btn", "btn-primary", "w-100", "mt-3");
        checkButton.textContent = "Check";
        checkButton.dataset.id = question_id;

        boxDomanda.appendChild(checkButton);
        container.appendChild(boxDomanda);

        checkButton.addEventListener("click", () => {
            let id = checkButton.getAttribute("data-id");
            let boxDomanda = document.querySelector("#box-domanda-" + id);
            let risposte = boxDomanda.querySelectorAll("input[type='radio']");
            let risposta_scelta = boxDomanda.querySelector("input[type='radio']:checked");
            if (risposta_scelta) {
                let isCorretta = false;
                risposte.forEach((risposta) => {
                    if (risposta.checked) {
                        let risposta_scelta_val = risposta.value;
                        let risposta_corretta = question.correct_answer;
                        if (risposta_scelta_val === risposta_corretta) {
                            isCorretta = true;
                        }
                    }
                });

                if (isCorretta) {
                    boxDomanda.classList.add("correct");
                    boxDomanda.classList.remove("wrong");
                    checkButton.disabled = true;
                    risposte.forEach((risposta) => {
                        risposta.disabled = true;
                    });
                } else {
                    boxDomanda.classList.add("wrong");
                    boxDomanda.classList.remove("correct");
                    risposte.forEach((risposta) => {
                        if (risposta.value === question.correct_answer) {
                            let label = risposta.parentElement.querySelector("label[for='" + risposta.id + "']");
                            label.classList.add("correct");
                            label.classList.remove("wrong");
                        }
                    });
                    checkButton.disabled = true;
                    risposte.forEach((risposta) => {
                        risposta.disabled = true;
                    });
                }
            }
        });
    });
}