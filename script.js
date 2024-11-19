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

// Array para armazenar os funcionários

let currentAction = null;
let selectedFuncionarioId = null; // Variável global para armazenar o ID do funcionário selecionado

// Função para carregar funcionários do banco de dados
function loadFuncionariosFromDatabase() {
    fetch('http://localhost:8080/as/funcionarios')
        .then(response => response.json())
        .then(data => {
            funcionarios = data; // Supondo que a resposta seja uma lista de funcionários
            updateFuncionariosList();
        })
        .catch(error => {
            console.error('Erro ao carregar funcionários:', error);
            alert('Ocorreu um erro ao carregar os funcionários.');
        });
}

// Função para atualizar a lista de funcionários
function updateFuncionariosList() {
    const container = document.getElementById('funcionariosList');
    container.innerHTML = '';
    funcionarios.forEach(func => {
        const div = document.createElement('div');
        div.className = 'funcionario-item';
        div.dataset.id = func.id;
        div.textContent = func.nome;
        div.onclick = () => {
            // Remove seleção anterior
            document.querySelectorAll('.funcionario-item').forEach(item => {
                item.classList.remove('selected');
            });
            // Adiciona seleção ao item clicado
            div.classList.add('selected');
            selectFuncionario(func.id);
        };
        container.appendChild(div);
    });
}

// Função para selecionar um funcionário
function selectFuncionario(id) {
    const funcionario = funcionarios.find(f => f.id == id);
    if (funcionario) {

        selectedFuncionarioId = funcionario.id; // Atualiza o ID do funcionário selecionado

        document.getElementById('nome').value = funcionario.nome;
        document.getElementById('idade').value = funcionario.idade;
        document.getElementById('cpf').value = funcionario.cpf;
        document.getElementById('email').value = funcionario.email;
        document.getElementById('telefone').value = funcionario.telefone;
        document.getElementById('cargo').value = funcionario.cargo;
        document.getElementById('acesso').value = funcionario.nivelAcesso;
    }
}

// Função para limpar o formulário
function clearForm() {
    document.getElementById('nome').value = '';
    document.getElementById('idade').value = '';
    document.getElementById('cpf').value = '';
    document.getElementById('email').value = '';
    document.getElementById('telefone').value = '';
    document.getElementById('cargo').value = '';
    document.getElementById('acesso').value = '';

    selectedFuncionarioId = null; // Limpa o ID do funcionário selecionado
}

// Função para verificar se todos os campos estão preenchidos
function validateForm() {
    const inputs = document.querySelectorAll('.form-input');
    return Array.from(inputs).every(input => input.value.trim() !== '');
}

// Desabilitar inputs inicialmente
document.querySelectorAll('.form-input').forEach(input => {
    input.disabled = true;
});

// Esconder botões de confirmação inicialmente
document.querySelector('.botoes-confirmacao').style.display = 'none';

// Event Listeners para os botões principais
document.querySelector('.btn-editar').addEventListener('click', function() {
    if (!selectedFuncionarioId) {
        alert('Selecione um funcionário para editar');
        return;
    }
    currentAction = 'edit';
    document.querySelectorAll('.form-input').forEach(input => {
        input.disabled = false;
    });
    document.querySelector('.botoes-principais').style.display = 'none';
    document.querySelector('.botoes-confirmacao').style.display = 'flex';
});

document.querySelector('.btn-deletar').addEventListener('click', function() {
    if (!selectedFuncionarioId) {
        alert('Selecione um funcionário para deletar');
        return;
    }

    currentAction = 'delete';
    document.querySelector('.botoes-principais').style.display = 'none';
    document.querySelector('.botoes-confirmacao').style.display = 'flex';
});

document.querySelector('.btn-cadastrar').addEventListener('click', function() {
    currentAction = 'create';
    clearForm();
    
    // Habilitar inputs para cadastro
    document.querySelectorAll('.form-input').forEach(input => {
        input.disabled = false;
    });
    
    // Ocultar botões principais e mostrar confirmação
    document.querySelector('.botoes-principais').style.display = 'none';
    document.querySelector('.botoes-confirmacao').style.display = 'flex';
});

// Event Listener para botão cancelar
document.querySelector('.btn-cancelar').addEventListener('click', function() {
    clearForm();
    
    // Desabilitar inputs e mostrar botões principais novamente
    document.querySelectorAll('.form-input').forEach(input => {
        input.disabled = true;
    });
    
    // Exibir botões principais novamente
    document.querySelector('.botoes-principais').style.display = 'flex';
    document.querySelector('.botoes-confirmacao').style.display = 'none';
    currentAction = null;
});

// Event Listener para botão confirmar
document.querySelector('.btn-confirmar').addEventListener('click', function() {
    if (currentAction === 'delete') {
        if (!selectedFuncionarioId) {
            alert('Nenhum funcionário selecionado para deletar.');
            return;
        }

        // Enviar requisição DELETE para o servidor
        fetch(`http://localhost:8080/as/funcionarios/${selectedFuncionarioId}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
            }
        })
        .then(response => {
            if (response.ok) {
                // Remover o funcionário da lista localmente e recarregar a lista do banco de dados
                funcionarios = funcionarios.filter(f => f.id !== selectedFuncionarioId);
                loadFuncionariosFromDatabase();
                clearForm();
                alert('Funcionário deletado com sucesso.');
            } else {
                throw new Error('Erro ao deletar funcionário.');
            }
        })
        .catch(error => {
            console.error('Erro ao deletar funcionário:', error);
            alert('Ocorreu um erro ao deletar o funcionário.');
        });

    } else if (currentAction === 'create' || currentAction === 'edit') {
        if (!validateForm()) {
            alert('Por favor, preencha todos os campos');
            return;
        }

        if (currentAction === 'create') {
            // Criar novo funcionário
            const newFuncionario = {
                nome: document.getElementById('nome').value,
                idade: parseInt(document.getElementById('idade').value),
                cpf: document.getElementById('cpf').value,
                email: document.getElementById('email').value,
                telefone: document.getElementById('telefone').value,
                cargo: document.getElementById('cargo').value, // Alterado para string
                nivelAcesso: parseInt(document.getElementById('acesso').value) // Mantido como int
            };

            // Enviar funcionário ao servidor
            fetch('http://localhost:8080/as/funcionarios', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(newFuncionario),
            })
                .then(response => response.json())
                .then(data => {
                    // Recarregar todos os funcionários do banco de dados
                    loadFuncionariosFromDatabase();
                    clearForm();
                })
                .catch(error => {
                    console.error('Erro ao cadastrar funcionário:', error);
                    alert('Ocorreu um erro ao cadastrar o funcionário.');
                });
        } else if (currentAction === 'edit') {
            const updatedFuncionario = {
                nome: document.getElementById('nome').value,
                idade: parseInt(document.getElementById('idade').value),
                cpf: document.getElementById('cpf').value,
                email: document.getElementById('email').value,
                telefone: document.getElementById('telefone').value,
                cargo: document.getElementById('cargo').value, // Alterado para string
                nivelAcesso: parseInt(document.getElementById('acesso').value) // Mantido como int
            };

            // Enviar funcionário atualizado ao servidor
            fetch(`http://localhost:8080/as/funcionarios/${selectedFuncionarioId}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(updatedFuncionario),
            })
                .then(response => response.json())
                .then(data => {
                    // Recarregar todos os funcionários do banco de dados
                    loadFuncionariosFromDatabase();
                    clearForm();
                })
                .catch(error => {
                    console.error('Erro ao atualizar funcionário:', error);
                    alert('Ocorreu um erro ao atualizar o funcionário.');
                });
        }
    }

    // Atualizar a lista e limpar o estado
    loadFuncionariosFromDatabase();
    clearForm();

    // Desabilitar inputs e mostrar botões principais novamente
    document.querySelectorAll('.form-input').forEach(input => {
        input.disabled = true;
    });
    document.querySelector('.botoes-principais').style.display = 'flex';
    document.querySelector('.botoes-confirmacao').style.display = 'none';
    currentAction = null;
});


// Inicializar a lista de funcionários ao carregar a página
document.addEventListener('DOMContentLoaded', loadFuncionariosFromDatabase);
