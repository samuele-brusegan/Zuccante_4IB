document.addEventListener("DOMContentLoaded", init)

function init() {
    let table = document.querySelector("#tavp");
    let k = 10;

    for (let i = 1; i <= k; i++) {
        let row = document.createElement("div");
        row.classList.add("row");
        
        for (let j = 1; j <= k; j++) {
            let col = document.createElement("div");
            col.classList.add("col");

            col.innerHTML = i * j

            row.appendChild(col);
        }

        table.appendChild(row);
    }
}