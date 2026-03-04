let data = {
  "articoli": [
    {
      "nome": "sensore infrarossi",
      "fileImg": "Art1SensorePIR.jpg",
      "tipologia": "sensori",
      "prezzo": 2.90
    },
    {
      "nome": "sensore di fiamma",
      "fileImg": "Art2SensoreFiamma.jpg",
      "tipologia": "sensori",
      "prezzo": 2.50
    },
    {
      "nome": "motor shield",
      "fileImg": "Art3MotorShield.jpg",
      "tipologia": "controllo",
      "prezzo": 19.90
    },
    {
      "nome": "servomotore",
      "fileImg": "Art4Servomotore.jpg",
      "tipologia": "motori",
      "prezzo": 5.90
    },
    {
      "nome": "motoriduttore",
      "fileImg": "Art5Motoriduttore.jpg",
      "tipologia": "motori",
      "prezzo": 65.90
    },
    {
      "nome": "display touch",
      "fileImg": "Art6DisplayTouch.jpg",
      "tipologia": "display",
      "prezzo": 89.90
    }
  ]
}

// Adding a id for each articolo
if (data !== null && data !== undefined && data.articoli.length !== 0) {
  let idMatrix = 0;
  data.articoli.forEach(el => {
    el.id = idMatrix;
    idMatrix++;
  })
  console.log(data.articoli);
}