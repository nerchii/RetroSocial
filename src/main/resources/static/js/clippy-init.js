// source : https://mklab.eu.org/clippy/

document.addEventListener("DOMContentLoaded", function () {
    clippy.BASE_PATH = "/clippy/agents/";

    clippy.load("Clippy", function(agent) {
        agent.show();
        agent.moveTo(300, 400);
        agent.speak("Hi! I'm Clippy");
    });
});
