document.addEventListener("DOMContentLoaded", async () => {
    const loader = document.getElementById("loader");
    const questionsContainer = document.getElementById("questions-container");
    const form = document.getElementById("quiz-config-form");

    // Carica le categorie disponibili
    await loadCategories();

    // Gestisci il submit del form
    form.addEventListener("submit", async (e) => {
        e.preventDefault();

        // Mostra loader
        loader.style.display = "block";
        questionsContainer.innerHTML = "";

        try {
            // Recupera i parametri dal form
            const category = document.getElementById("category-select").value;
            const difficulty = document.getElementById("difficulty-select").value;
            const type = document.getElementById("type-select").value;
            const amount = parseInt(document.getElementById("amount-input").value);

            // Chiama API con i parametri selezionati
            const domande = await getQuestionsWithRetry(amount, difficulty, type, category, 5);
            hideLoader(loader);

            if (!domande || !domande.results || domande.results.length === 0) {
                showError(questionsContainer, "Nessuna domanda disponibile con i criteri selezionati. Prova a modificare i filtri.");
                return;
            }

            displayQuestions(domande.results, questionsContainer);
        } catch (error) {
            hideLoader(loader);
            console.error("Errore nel caricamento delle domande:", error);
            showError(questionsContainer, "Errore nel caricamento delle domande. Riprova tra qualche secondo.");
        }
    });
});

/**
 * Carica le categorie disponibili dall'API
 */
async function loadCategories() {
    const categorySelect = document.getElementById("category-select");

    try {
        const response = await fetch("https://opentdb.com/api_category.php");
        if (response.status === 200) {
            const data = await response.json();
            if (data && data.trivia_categories) {
                data.trivia_categories.forEach(category => {
                    const option = document.createElement("option");
                    option.value = category.id;
                    option.textContent = category.name;
                    categorySelect.appendChild(option);
                });
            }
        }
    } catch (error) {
        console.warn("Impossibile caricare le categorie:", error.message);
    }
}

/**
 * Fetch domande con retry logic
 * Aspetta 5 secondi e riprova se status != 200 o response == undefined
 */
async function getQuestionsWithRetry(amount, difficulty = "", type = "", category = "", maxRetries = 5) {
    const params = new URLSearchParams();
    params.append("amount", amount);

    if (difficulty) params.append("difficulty", difficulty);
    if (type) params.append("type", type);
    if (category) params.append("category", category);

    const url = `https://opentdb.com/api.php?${params.toString()}`;
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