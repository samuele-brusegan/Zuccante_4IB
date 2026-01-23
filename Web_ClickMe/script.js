let escapeRange = "40px";
let escapeAmount = 120;
document.addEventListener("DOMContentLoaded", init);

function init(){
    let wrapperBtn = document.querySelector(".wrapper-btn");
    //hover
    wrapperBtn.addEventListener("mouseover", moveBtn);

    function moveBtn(){
        let btnPosition = wrapperBtn.getBoundingClientRect();

        let x = Math.random() * escapeAmount*2 - escapeAmount;
        let y = Math.random() * escapeAmount*2 - escapeAmount;

        
        let computedX = wrapperBtn.offsetLeft + x;
        let computedY = wrapperBtn.offsetTop  + y;
        
        console.log(x, y);
        console.log(computedX, computedY);
        
        // move btn
        wrapperBtn.style.top  = computedY + "px";
        wrapperBtn.style.left = computedX + "px";

        // check if btn is out of bounds
        if (computedX < 0 || computedX > window.innerWidth - btnPosition.width || computedY < 0 || computedY > window.innerHeight - btnPosition.height) {
            //reset to ecnter
            wrapperBtn.style.top  = "50%";
            wrapperBtn.style.left = "50%";
        }
    }
}