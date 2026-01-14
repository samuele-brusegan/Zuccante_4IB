<?php

$recordInstruction = [
    [
        "dt"=>"2020 - Roma, Italia",
        "tt"=>"Laurea magistrale in relazioni internazionali",
        "st"=>"Università di Roma LA SAPIENZA"
    ],
    [
        "dt"=>"2018 - Roma, Italia",
        "tt"=>"Diploma Liceo Linguistico",
        "st"=>"Liceo Classico Statale B. Russel",
    ]
];
$recordExperience = [
    [
        "dt"=>"2022 - ATTUALE - Milano, Italia",
        "tt"=>"ASSISTENTE DI DIREZIONE",
        "st"=>"Carrefour",
        "sp"=>[
            "Organizzazione e archiviazione di pratiche e documenti di lavoro e personali.",
            "Gestione e archiviazione della corrispondenza.",
            "Organizzazione meeting e affiancamento al Manager.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. In lorem libero, pellentesque placerat erat a, egestas mattis nisl. In consequat tincidunt lectus vel tincidunt. Nullam mattis nulla ac felis malesuada, eu fermentum mauris vulputate. Morbi vestibulum ornare nisi non ultrices. In mauris felis, vestibulum in ullamcorper id, pellentesque eu sem."
        ]
    ],
    [
        "dt"=>"01/09/2020 - 05/03/2021 - Roma, Italia",
        "tt"=>"ASSISTENTE DI DIREZIONE",
        "st"=>"Nike",
        "sp"=>[
            "Supporto a 360 all'Executive.",
            "Gestione e archiviazione della corrispondenza.",
            "Organizzazione Business Travel.",
            "Organizzazione meeting e affiancamento al Manager."
        ]
    ],
];
$recordLanguages = [
    "motherLanguage" => "Italaino"
];
$recordInstruction = [""];

?>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Curriculum</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="style.css">
    </head>
    <body>
        <div class="container">
            <div style="display: flex; justify-content: end;">
                <img src="LogoEuropass.jpg" alt="">
            </div>
            <div class="d-flex flex-column flex-sm-row">
                <img src="image.jpg" alt="" style="display: inline-block; border: 2px solid var(--gray-header); aspect-ratio: 1; height: 100%;"/>
                <div style="width: 100%; margin-left: 25px;">
                    <h1 class="cv-name">Maria Rossi</h1>
    
                    <div class="d-flex justify-content-between flex-wrap">
                        <div style="margin-right: 7px;"><b>Nazionalità:</b>&nbsp;Italiana</div>
                        <div style="margin-right: 7px;">(+39) 123 456 7890</div>
                        <div style="margin-right: 7px;"><a href="0:maria.rossi@mail.com">maria.rossi@mail.com</a></div>
                        <div style="margin-right: 7px;"><a href="https://maria.rossi/linkedin">maria.rossi/linkedin</a></div>
                    </div>
                    <p>
                        Presentazione: <br>
                        Ho esperienza di diversi anni come Assistente di direzione, amo il mio lavoro e mi
                        piacerebbe mettermi alla prova in un altro settore. La vosra azienda mi interessa
                        particolarmente. Sono attenta ed entusiasta, mi impegno per portare sempre a
                        termine i miei progetti. Spero di poter condividere la mia esperienza con voi.
                    </p>
                </div>
            </div>
    
            <div class="section">
                <div class="subtitle">
                    <div>
                        <svg>
                            <circle r="6px" cx="7px" cy="7px">
                        </svg>
                        <div class="st">Istruzione e formazione</div>
                    </div>
                </div>
                <?php
                foreach ($recordInstruction as $el){
                    ?>
                    <div class="mb-2">
                        <span class="record-datetime"><?=$el['dt']?></span> <br>
                        <span class="record-title"><?=$el['tt']?></span> - 
                        <span class="record-subtitle"><?=$el['st']?></span>
                    </div>
                    <?php
                }
                ?>
            </div>
    
            <div class="section">
                <div class="subtitle">
                    <div>
                        <svg>
                            <circle r="6px" cx="7px" cy="7px">
                        </svg>
                        <div class="st">Esperienza lavorativa</div>
                    </div>
                </div>
                <?php
                foreach ($recordExperience as $el){
                    ?>
                    <div class="mb-2">
                        <span class="record-datetime"><?=$el['dt']?></span> <br>

                        <span class="record-title"><?=$el['tt']?></span> - <spam class="record-where"><?=$el['st']?></spam>
                        <ul>
                        <?php
                        foreach ($el['sp'] as $listEl){
                            echo "<li>".$listEl."</li>\n";
                        }
                        ?>
                        </ul>
                    </div>
                    <?php
                }
                ?>
            </div>
    
            <div class="section">
                <div class="subtitle">
                    <div>
                        <svg>
                            <circle r="6px" cx="7px" cy="7px">
                        </svg>
                        <div class="st">Competenze Linguistiche</div>
                    </div>
                </div>
                <div>
                    Lingua madre: <b>ITALIANO</b><br>
                    Altre lingue: <br>
                    <div class="h-list">
                        <div> <b>SPAGNOLO</b> </div>
                        <div> <b>INGLESE </b> </div>
                        <div> <b>FRANCESE</b> </div>
                    </div>
                </div>
            </div>
    
            <div class="section">
                <div class="subtitle">
                    <div>
                        <svg>
                            <circle r="6px" cx="7px" cy="7px">
                        </svg>
                        <div class="st">Competenze Diglitali</div>
                    </div>
                </div>
                <div>
                    <div class="h-list">
                        <div> Microsoft Word </div>
                        <div> Microsoft Excel </div>
                        <div> Microsoft Power Point </div>
                        <div> Social Media </div>
                        <div> Outlook </div>
                    </div>
                </div>
            </div>
    
            <div class="section">
                <div class="subtitle">
                    <div>
                        <svg>
                            <circle r="6px" cx="7px" cy="7px">
                        </svg>
                        <div class="st">Patente di Guida</div>
                    </div>
                </div>
                <div>
                    <b>Patente di Guida: B</b>
                </div>
            </div>
        </div>
        
        
    </body>
</html>
