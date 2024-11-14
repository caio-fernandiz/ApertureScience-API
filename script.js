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


document.querySelectorAll('.form-input').forEach(input => {
    input.disabled = true;
});


document.querySelector('.botoes-confirmacao').style.display = 'none';


document.querySelector('.btn-editar').addEventListener('click', function() {
    document.querySelectorAll('.form-input').forEach(input => {
        input.disabled = false;
    });
    document.querySelector('.botoes-principais').style.display = 'none';
    document.querySelector('.botoes-confirmacao').style.display = 'flex';
});


document.querySelector('.btn-deletar').addEventListener('click', function() {
    document.querySelector('.botoes-principais').style.display = 'none';
    document.querySelector('.botoes-confirmacao').style.display = 'flex';
});


document.querySelector('.btn-cancelar').addEventListener('click', function() {
    document.querySelectorAll('.form-input').forEach(input => {
        input.disabled = true;
    });
    document.querySelector('.botoes-principais').style.display = 'flex';
    document.querySelector('.botoes-confirmacao').style.display = 'none';
});


document.querySelector('.btn-confirmar').addEventListener('click', function() {
    document.querySelectorAll('.form-input').forEach(input => {
        input.disabled = true;
    });
    document.querySelector('.botoes-principais').style.display = 'flex';
    document.querySelector('.botoes-confirmacao').style.display = 'none';

});


document.querySelector('#funcionarioSelect').addEventListener('change', function(e) {

});

