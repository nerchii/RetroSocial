let topZIconIndex = 10;

document.addEventListener('DOMContentLoaded', () => {
    topZIconIndex ++;

    document.querySelectorAll('.desktop-icon').forEach(icon => {
        icon.addEventListener('mousedown', e => {
            e.preventDefault();

            topZIconIndex++;
            icon.style.zIndex = topZIconIndex;

            const rect = icon.getBoundingClientRect();
            const offsetX = e.clientX - rect.left;
            const offsetY = e.clientY - rect.top;

            icon.classList.add('dragging');

            function onMouseMove(e) {
                icon.style.left = `${e.clientX - offsetX}px`;
                icon.style.top = `${e.clientY - offsetY}px`;
            }

            function onMouseUp() {
                icon.classList.remove('dragging');
                document.removeEventListener('mousemove', onMouseMove);
                document.removeEventListener('mouseup', onMouseUp);
            }

            document.addEventListener('mousemove', onMouseMove);
            document.addEventListener('mouseup', onMouseUp);
        });
    });
});
