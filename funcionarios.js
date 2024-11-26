// Elementos do DOM
const funcionariosList = document.getElementById('funcionariosList');
const inputs = document.querySelectorAll('.form-inputF');
const btnCadastrar = document.querySelector('.btn-cadastrarF');
const btnEditar = document.querySelector('.btn-editarF');
const btnDeletar = document.querySelector('.btn-deletarF');
const btnConfirmar = document.querySelector('.btn-confirmarF');
const btnCancelar = document.querySelector('.btn-cancelarF');
const botoesPrincipais = document.querySelector('.botoes-principaisF');
const botoesConfirmacao = document.querySelector('.botoes-confirmacaoF');

// Estado da aplicação
let funcionarioSelecionado = null;
let acaoAtual = null; // 'cadastrar', 'editar' ou 'deletar'

// Esconder botões de confirmação inicialmente
botoesConfirmacao.style.display = 'none';

// Desabilitar inputs inicialmente
function desabilitarInputs() {
    inputs.forEach(input => input.disabled = true);
}

// Habilitar inputs
function habilitarInputs() {
    inputs.forEach(input => input.disabled = false);
}

// Inicializar com inputs desabilitados
desabilitarInputs();

// Função para carregar funcionários
async function carregarFuncionarios() {
    try {
        const response = await fetch('http://localhost:8080/as/funcionarios');
        const funcionarios = await response.json();
        
        funcionariosList.innerHTML = '';
        funcionarios.forEach(funcionario => {
            const div = document.createElement('div');
            div.className = 'funcionario-item';
            div.textContent = funcionario.nome;
            div.addEventListener('click', () => selecionarFuncionario(funcionario));
            funcionariosList.appendChild(div);
        });
    } catch (error) {
        console.error('Erro ao carregar funcionários:', error);
    }
}

// Função para selecionar funcionário
function selecionarFuncionario(funcionario) {
    funcionarioSelecionado = funcionario;
    // Preencher inputs com dados do funcionário
    document.getElementById('nome').value = funcionario.nome;
    document.getElementById('idade').value = funcionario.idade;
    document.getElementById('cpf').value = funcionario.cpf;
    document.getElementById('email').value = funcionario.email;
    document.getElementById('telefone').value = funcionario.telefone;
    document.getElementById('cargo').value = funcionario.cargo;
    document.getElementById('acesso').value = funcionario.acesso;
    // Manter inputs desabilitados ao selecionar
    desabilitarInputs();
}

// Função para limpar inputs
function limparInputs() {
    inputs.forEach(input => input.value = '');
    funcionarioSelecionado = null;
}

// Função para mostrar/esconder botões
function toggleBotoes(mostrarConfirmacao) {
    botoesPrincipais.style.display = mostrarConfirmacao ? 'none' : 'flex';
    botoesConfirmacao.style.display = mostrarConfirmacao ? 'flex' : 'none';
}

// Event Listeners para botões principais
btnCadastrar.addEventListener('click', () => {
    limparInputs();
    habilitarInputs();
    acaoAtual = 'cadastrar';
    toggleBotoes(true);
});

btnEditar.addEventListener('click', () => {
    if (!funcionarioSelecionado) return;
    habilitarInputs();
    acaoAtual = 'editar';
    toggleBotoes(true);
});

btnDeletar.addEventListener('click', () => {
    if (!funcionarioSelecionado) return;
    acaoAtual = 'deletar';
    toggleBotoes(true);
});

// Event Listener para botão cancelar
btnCancelar.addEventListener('click', () => {
    if (acaoAtual === 'cadastrar') {
        limparInputs();
    } else if (acaoAtual === 'editar') {
        selecionarFuncionario(funcionarioSelecionado);
    }
    desabilitarInputs();
    toggleBotoes(false);
});

// Event Listener para botão confirmar
btnConfirmar.addEventListener('click', async () => {
    try {
        let url = 'http://localhost:8080/as/funcionarios';
        let method = 'POST';
        let body = {
            nome: document.getElementById('nome').value,
            idade: parseInt(document.getElementById('idade').value),
            cpf: document.getElementById('cpf').value,
            email: document.getElementById('email').value,
            telefone: document.getElementById('telefone').value,
            cargo: document.getElementById('cargo').value,
            acesso: parseInt(document.getElementById('acesso').value)
        };

        if (acaoAtual === 'editar') {
            url += `/${funcionarioSelecionado.id}`;
            method = 'PUT';
        } else if (acaoAtual === 'deletar') {
            url += `/${funcionarioSelecionado.id}`;
            method = 'DELETE';
            body = null;
        }

        const response = await fetch(url, {
            method: method,
            headers: body ? {
                'Content-Type': 'application/json'
            } : {},
            body: body ? JSON.stringify(body) : null
        });

        if (response.ok) {
            await carregarFuncionarios();
            limparInputs();
            desabilitarInputs();
            toggleBotoes(false);
        } else {
            throw new Error('Erro na operação');
        }
    } catch (error) {
        console.error('Erro:', error);
    }
});

// Carregar funcionários ao iniciar
carregarFuncionarios();