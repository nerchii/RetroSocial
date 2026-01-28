let topZIndex = 1;

document.addEventListener('DOMContentLoaded', () => {
    document.querySelectorAll('.retro-window').forEach(windowEl => {

        const rect = windowEl.getBoundingClientRect();
        windowEl.style.position = 'absolute';
        windowEl.style.left = (window.innerWidth - rect.width) / 2 + 'px';
        windowEl.style.top = (window.innerHeight - rect.height) / 2 + 'px';

        const header = windowEl.querySelector('.window-title-bar');
        let offsetX, offsetY, isDragging = false;

        header.addEventListener('mousedown', e => {
            isDragging = true;
            topZIndex++;
            windowEl.style.zIndex = topZIndex;

            const rect = windowEl.getBoundingClientRect();
            offsetX = e.clientX - rect.left;
            offsetY = e.clientY - rect.top;

            document.body.style.userSelect = 'none';
        });

        document.addEventListener('mousemove', e => {
            if (isDragging) {
                windowEl.style.left = (e.clientX - offsetX) + 'px';
                windowEl.style.top = (e.clientY - offsetY) + 'px';
            }
        });

        document.addEventListener('mouseup', () => {
            isDragging = false;
            document.body.style.userSelect = 'auto';
        });
    });
});


function test(id) {
        fetch("/api/posts/" + id, {
            method: "DELETE"
        });
        reloadData();
    }

function reloadData() {
    $.ajax({
        url: "/api/posts",
        type: "GET",
        contentType: "application/json; charset=utf-8",
        success: function (resultData) {
            console.log(resultData);

            $("#posts").empty();

            $.each(resultData, function (index, post) {
                $("#posts").append(`
                    <div class="post">
                        <p class="post-content">${post.content}</p>
                        <small class="post-date">${new Date(post.createdAt).toLocaleString()}</small>
                        <button type="button" onclick="test(${post.id})">Delete post</button>
                    </div>
                `);
            });
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.error("Error:", textStatus);
        },
        timeout: 120000
    });
}

reloadData();


