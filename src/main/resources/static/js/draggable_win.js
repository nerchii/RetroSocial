let topZIndex = 100;

document.addEventListener('DOMContentLoaded', () => {
    document.querySelectorAll('.retro-window').forEach(windowEl => {

        const rect = windowEl.getBoundingClientRect();
        windowEl.style.position = 'absolute';

        const viewportWidth = window.innerWidth;
        const viewportHeight = window.innerHeight;

        let left, top;
        if (windowEl.id === 'chatterboxWindow') {
            left = (viewportWidth - rect.width) / 2 - 200;   // left
            top  = (viewportHeight - rect.height) / 2 - 100; // up
        } else if (windowEl.id === 'gameWindow') {
            left = (viewportWidth - rect.width) / 2 + 200;   // right
            top  = (viewportHeight - rect.height) / 2 + 50; // down
        } else {
            // default: center
            left = (viewportWidth - rect.width) / 2;
            top = (viewportHeight - rect.height) / 3;
        }

        windowEl.style.left = `${left}px`;
        windowEl.style.top = `${top}px`;

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

        windowEl.addEventListener('mousedown', () => {
            topZIndex++;
            windowEl.style.zIndex = topZIndex;
        });
    });
});
