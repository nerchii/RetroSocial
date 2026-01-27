// source : https://mklab.eu.org/clippy/

document.addEventListener("DOMContentLoaded", function () {
    clippy.BASE_PATH = "/clippy/agents/";

    clippy.load("Clippy", function (agent) {
        agent.show();
        // agent.moveTo(300, 400);
        agent.speak("Hi! I'm Clippy, your personal assistant, if you need help just say the word!");

        agent._el.on('contextmenu', function (e) {
            e.preventDefault();

            $('.clippy-input').remove();

            var input = $('<textarea class="clippy-input" placeholder="Ask me something..."></textarea>');

            input.css({
                position: 'absolute', top: e.pageY + 'px', left: e.pageX + 'px', zIndex: 9999
            });

            $('body').append(input);
            input.focus();
            input.on('input', function () {
                this.style.height = 'auto';
                this.style.height = this.scrollHeight + 'px';
            });


            input.on('keydown', function (ev) {
                if (ev.key === 'Enter') {
                    var text = input.val();
                    input.remove();
                    if (text) {
                        agent.speak(local(text));
                    }
                }
            });
        });
    });
})

function local(text) {
    text = text.toLowerCase();

    if (text.includes("hello")||text.includes("hi")) return "Hello! How can I help you?";
    if (text.includes("post")||text.includes("forum")) return "You can post messages in the Chatterbox95 forum.";
    if (text.includes("bye")) return "Goodbye! I'll just hang around here.";
    if (text.includes("settings")) return "Open the Control Panel to change your settings.";
    if (text.includes("clock")) return "The clock is on the taskbar at the bottom right of your screen.";
    if (text.includes("browser")) return "Click the Web Browser icon to surf the internet.";
    if (text.includes("recycle")) return "Drag items to the Recycle Bin to delete them, but you can restore later!";
    if (text.includes("help")) return "Try clicking around the desktop or ask me anything!";
    if (text.includes("game")) return "There are fun games in the Games folder!";
    if (text.includes("email")) return "Click the Mail icon to check your messages.";

    if (text.includes("fanart")) return "I dont want to talk about it.... please...";



    return "I'm not sure how to help with that yet. :( ";
}


