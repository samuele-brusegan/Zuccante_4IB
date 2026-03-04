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
<!DOCTYPE HTML>
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
            <div style="text-align: center;">
                <h3>Vivaio</h3>
                <h5>"La grande Quercia che si piega ma non si spezza"</h5>
            </div>
            <img src="img/map.svg" alt="" />
        </header>

        <nav>
            <div></div>
            <a class="btn" href="index.html">Homepage</a>
            <div></div>
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
                    secDom.style.overflow = "auto";
                    secDom.style.width = (100/data.length-.1)+"%";
                    secDom.style.margin = "10px";
                    secDom.classList.add('list');

                    let h6 = document.createElement("h4");
                    h6.innerHTML = sec['name'];
                    secDom.appendChild(h6);

                    let list = sec.list;
                    
                    list.forEach(plant => {
                        let div1 = document.createElement("div");
                        div1
                        div1.innerHTML = `
                        <!--src="${plant.imag}"-->
                        <img src="https://picsum.photos/200" />
                        <div>
                            <b>${plant.name}</b>
                            <p>${plant.text}</p>
                        </div>
                        `;
                        div1.style.display = 'flex';
                        div1.style.margin = '20px 10px';
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
