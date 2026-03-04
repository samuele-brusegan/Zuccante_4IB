document.addEventListener("DOMContentLoaded", init);

let state = {
    carrello: []
}

function init() {
    const articleList = document.querySelector(".article-list#buy");
    const carrelloDom = document.querySelector(".article-list#carrello");
    const calcolaPrezzoBtn = document.querySelector("#calcolaPrezzo");
    calcolaPrezzoBtn.addEventListener("click", calcolaTotale)

    state.articleList = articleList;
    state.carrelloDomList = carrelloDom;

    populateArticleList(articleList);
}

function populateArticleList(articleList) {
    if (!data) {
        console.error("Data is not set!");
        return;
    }

    let articles = data.articoli;
    if (articles === null || articles === undefined) {
        console.error("Articles null or undefined");
        return;
    }

    articles.forEach(el => {
        /*
        <article class="card prodotto">
            <img src="assets/images/Art1SensorePIR.jpg" alt="" srcset="" />
            <div>
                <h4>nome</h4>
                <p>categoria</p>
                <p>prezzo</p>
            </div>
        </article>
        */

        console.log(el);
        const domEl = document.createElement("article");
        domEl.classList.add("card", "prodotto", "hover");
        domEl.innerHTML = `<img src="assets/images/${el.fileImg}" alt="" srcset="" />`;
        
        const domArticleData = document.createElement("div");
        domArticleData.innerHTML = `
        <h4>${el.nome}</h4>
        <p>${el.tipologia}</p>
        <p>${el.prezzo}</p>
        `
        domEl.appendChild(domArticleData);

        
        articleList.appendChild(domEl);        
        
        // Per scelta si piò cliccare su tutto l'elemento per aggingerlo al carrello non solo sull'immagine
        domEl.addEventListener("click", () => {
            //Aggiungo al carrello
            state.carrello.push(el)
            updateCarrello();
            console.log(state.carrello);
            
        });
    });
    
}

function updateCarrello() {
    let carrello = state.carrello;
    let domList = state.carrelloDomList;
    domList.innerHTML = "";

    carrello.forEach((el, i) => {
        if (el === null) return;
        const id = el.id;

        const domEl = document.createElement("article");
        domEl.classList.add("card", "prodotto", "removable");
        domEl.innerHTML = `<img src="assets/images/${el.fileImg}" alt="" srcset="" />`;
        
        const domArticleData = document.createElement("div");
        domArticleData.innerHTML = `
        <h4>${el.nome}</h4>
        <p>${el.tipologia}</p>
        <p>${el.prezzo}</p>
        `
        domEl.appendChild(domArticleData);

        let remove = document.createElement("button");
        remove.classList.add("remove");
        remove.innerHTML = "X";
        domEl.appendChild(remove);
        
        domList.appendChild(domEl);

        remove.addEventListener("click", () => {
            //rimuovo dal carrello
            let idToRemove = id;
            let newCarrello = [];
            carrello.forEach(el => {
                if (el.id !== idToRemove) newCarrello.push(el);
            });
            state.carrello = newCarrello
            // aggiorno
            updateCarrello();
        })
    });
}

function calcolaTotale() {
    const prezzoCalcolato = document.querySelector("#prezzoCalcolato");
    let prezzo = 0;

    if (state.carrello.length !== 0) {
        
        state.carrello.forEach(el => {
            if (el === null) return;
            // controlla che sia intero
            //let currPrezzo = Integer.parse(el.prezzo)
            let currPrezzo = el.prezzo;
            prezzo += currPrezzo;
        });

    }
    let prezzoStr = prezzo + "";
    let cifreIntere = prezzoStr.indexOf(".");
    if (cifreIntere === -1) cifreIntere = prezzoStr.length;

    prezzoCalcolato.innerHTML = prezzo.toPrecision(cifreIntere+2) + " €"
}