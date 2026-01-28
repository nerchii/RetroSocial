document.getElementById("postForm").addEventListener("submit", function (e) {
    e.preventDefault();

    const content = document.getElementById("content").value;

    fetch("/api/posts", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            content: content
        })
    })
    .then(res => res.json())
    .then(() => {
        document.getElementById("content").value = "";
        reloadData();
    })
    .catch(err => console.error(err));
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
                  <div class="retro-post">
                    <p class="post-content">${post.content}</p>
                    <small class="post-date">
                      ${new Date(post.createdAt).toLocaleString()}
                    </small>
                    <button class="retro-button" type="button" onclick="test(${post.id})">
                      Delete post
                    </button>
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


