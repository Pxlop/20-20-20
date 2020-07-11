var seconds;
var interval;
var running = false;

const timerText = document.getElementById("timerText");
const startBtn = document.getElementById("startBtn");
const stopBtn = document.getElementById("stopBtn");
const alertAudio = new Audio("AlarmAudio.wav");

startBtn.onclick = () => {
    running = true;
    start20MinsTimer();
};

stopBtn.onclick = () => {
    running = false;
}

function start20MinsTimer() {
    seconds = 1200;
    console.log("20 minutes timer has started");
    interval = setInterval(() => {
        if(!running) clearInterval(interval);
        seconds --;
        var displayMinutes = Math.floor(seconds / 60);
        var displaySeconds = seconds % 60;
        console.log(seconds);
        if(displayMinutes < 10 && displaySeconds < 10) timerText.innerHTML = "0" + displayMinutes + ":0" + displaySeconds;
        else if(displaySeconds < 10) timerText.innerHTML = displayMinutes + ":0" + displaySeconds;
        else if(displayMinutes < 10) timerText.innerHTML = "0" + displayMinutes + ":" + displaySeconds;
        else timerText.innerHTML = displayMinutes + ":" + displaySeconds;
        if(!seconds) {
            clearInterval(interval);
            console.log("20 minutes timer has ended");
            start20SecsTimer();
            alertAudio.play();
            window.alert("20 minutes are up, rest your eyes!");
        }
    }, 1000);
}

function start20SecsTimer() {
    seconds = 20;
    console.log("20 seconds timer has started");
    interval = setInterval(() => {
        if(!running) clearInterval(interval);
        seconds --;
        var displayMinutes = Math.floor(seconds / 60);
        var displaySeconds = seconds % 60;
        console.log(seconds);
        if(displayMinutes < 10 && displaySeconds < 10) timerText.innerHTML = "0" + displayMinutes + ":0" + displaySeconds;
        else if(displaySeconds < 10) timerText.innerHTML = displayMinutes + ":0" + displaySeconds;
        else if(displayMinutes < 10) timerText.innerHTML = "0" + displayMinutes + ":" + displaySeconds;
        else timerText.innerHTML = displayMinutes + ":" + displaySeconds;
        if(!seconds) {
            clearInterval(interval);
            console.log("20 seconds timer has ended");
            start20MinsTimer();
        }
    }, 1000);
}