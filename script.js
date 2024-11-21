function showSlide(index) {
    document.querySelectorAll('.slide').forEach(slide => {
        slide.classList.remove('active');
    });
    document.querySelectorAll('.opcoes h2').forEach(button => {
        button.classList.remove('active');
    });

    const slides = document.querySelectorAll('.slide');
    const buttons = document.querySelectorAll('.opcoes h2');
    
    slides[index].classList.add('active');
    buttons[index].classList.add('active');
}

showSlide(0);


