document.getElementById("postForm").addEventListener("submit", function (e) {
    e.preventDefault();

    const username = document.getElementById("username").value;
    const content = document.getElementById("content").value;

    let url = "/api/posts";
    if (username && username.trim() !== "") {
        url += "?username=" + encodeURIComponent(username.trim());
    }

    fetch(url, {
        method: "POST", headers: {
            "Content-Type": "application/json"
        }, body: JSON.stringify({
            content: content
        })
    })
        .then(res => res.json())
        .then(() => {
            document.getElementById("username").value = "";
            document.getElementById("content").value = "";
            reloadData();
        })
        .catch(err => console.error(err));
});

function deletePost(id) {
    fetch("/api/posts/" + id, {
        method: "DELETE"
    })
        .then(() => {
            reloadData();
        })
        .catch((err) => console.error(err));
}

function reloadData() {
    $.ajax({
        url: "/api/posts", type: "GET", contentType: "application/json; charset=utf-8", success: function (resultData) {
            console.log(resultData);

            $("#posts").empty();
            resultData.reverse();

            $.each(resultData, function (index, post) {
                $("#posts").append(`
                  <div class="retro-post">
                    <small class="post-username">${(post.user && post.user.username) || 'guest'}</small>
                    
                    <p class="post-content">${post.content}</p>
                    
                    <small class="post-date">${post.createdAt ? new Date(post.createdAt).toLocaleString() : ''}</small>
                    
                    <button class="post-delete-btn" type="button" onclick="deletePost(${post.id})">Delete post</button>
                  </div>
                `);
            });
        }, error: function (jqXHR, textStatus, errorThrown) {
            console.error("Error:", textStatus);
        }, timeout: 120000
    });
}

reloadData();


