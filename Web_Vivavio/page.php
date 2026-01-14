<?php
$jsonData = file_get_contents("data.json");
$jsonKey = "";
if (isset($_GET) && isset($_GET['id'])){
    $id = $_GET['id'];

    switch ($id) {
        case 0:
            $jsonKey = "pianteDaInterno";
            break;
        case 1:
            $jsonKey = "alberiEArbusti";
            break;
        case 2:
            $jsonKey = "bonsai";
            break;
    }
}
?>

<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Vivaio 3</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" />
        <link rel="stylesheet" href="css/style.css" />
    </head>
    <body>
        <header>
            <img src="img/logo.svg" alt="logo" />
            Vivaio "La grande Quercia"
            <img src="img/map.svg" alt="" />
        </header>

        <nav>
            <button class="btn">Piante da interno</button>
            <button class="btn">Alberi e arbusti</button>
            <button class="btn">Bonsai</button>
        </nav>

        <main id="container"></main>
        <script>
            function main() {
                let rawData = `<?=$jsonData?>`;
                let jsonKey = `<?=$jsonKey?>`;
                let container = document.getElementById("container");

                let data = JSON.parse(rawData);

                console.log(data);
                console.log(jsonKey);

                if (jsonKey == "") return;

                data = data[jsonKey];

                console.log(data);

                data.forEach(sec => {
                    let secDom = document.createElement("section");
                    secDom.style.height = "70vh";
                    secDom.style.overflow = "auto"
                    secDom.classList.add('list');

                    let h6 = document.createElement("h6");
                    h6.innerHTML = sec['name'];
                    secDom.appendChild(h6);

                    let list = sec.list;
                    
                    list.forEach(plant => {
                        let div1 = document.createElement("div");
                        div1.innerHTML = `
                        <img src="${plant.imag}" style="width: 40%">
                        <div>
                            <a>${plant.name}</a>
                            <p>${plant.text}</p>
                        </div>
                        `;
                        div1.style.display = 'flex';
                        div1.style.margin = '10px';
                        div1.style.textAlign = 'left';
                        secDom.appendChild(div1);
                    });

                    container.appendChild(secDom);
                });
            }
            document.addEventListener("DOMContentLoaded", main());
        </script>
        <footer>
            Copyleft 1992 - MIT Licence - ITIS "C. Zuccante"
        </footer>
    </body>
</html>
