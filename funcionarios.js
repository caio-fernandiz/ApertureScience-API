// Elementos do DOM
const funcionariosList = document.getElementById('funcionariosList');
const inputsF = document.querySelectorAll('.form-inputF');
const btnCadastrarF = document.querySelector('.btn-cadastrarF');
const btnEditarF = document.querySelector('.btn-editarF');
const btnDeletarF = document.querySelector('.btn-deletarF');
const btnConfirmarF = document.querySelector('.btn-confirmarF');
const btnCancelarF = document.querySelector('.btn-cancelarF');
const botoesPrincipaisF = document.querySelector('.botoes-principaisF');
const botoesConfirmacaoF = document.querySelector('.botoes-confirmacaoF');

// Estado da aplicação
let funcionarioSelecionado = null;
let acaoAtualF = null; // 'cadastrar', 'editar' ou 'deletar'

// Esconder botões de confirmação inicialmente
botoesConfirmacaoF.style.display = 'none';

// Desabilitar inputsF inicialmente
function desabilitarinputsF() {
    inputsF.forEach(input => input.disabled = true);
}

// Habilitar inputsF
function habilitarinputsF() {
    inputsF.forEach(input => input.disabled = false);
}

// Inicializar com inputsF desabilitados
desabilitarinputsF();

// Função para carregar funcionários
async function carregarFuncionarios() {
    try {
        const response = await fetch('http://localhost:8080/as/employees');
        const funcionarios = await response.json();
        
        funcionariosList.innerHTML = '';
        funcionarios.forEach(funcionario => {
            const div = document.createElement('div');
            div.className = 'funcionario-item';
            div.textContent = funcionario.name;
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
    // Preencher inputsF com dados do funcionário
    document.getElementById('nome').value = funcionario.name;
    document.getElementById('idade').value = funcionario.age;
    document.getElementById('cpf').value = funcionario.cpf;
    document.getElementById('email').value = funcionario.email;
    document.getElementById('telefone').value = funcionario.telephone;
    document.getElementById('cargo').value = funcionario.role;
    document.getElementById('acesso').value = funcionario.accessLevel;
    // Manter inputsF desabilitados ao selecionar
    desabilitarinputsF();
}

// Função para limpar inputsF
function limparinputsF() {
    inputsF.forEach(input => input.value = '');
    funcionarioSelecionado = null;
}

// Função para mostrar/esconder botões
function toggleBotoesF(mostrarConfirmacao) {
    botoesPrincipaisF.style.display = mostrarConfirmacao ? 'none' : 'flex';
    botoesConfirmacaoF.style.display = mostrarConfirmacao ? 'flex' : 'none';
}

// Event Listeners para botões principais
btnCadastrarF.addEventListener('click', () => {
    limparinputsF();
    habilitarinputsF();
    acaoAtualF = 'cadastrar';
    toggleBotoesF(true);
});

btnEditarF.addEventListener('click', () => {
    if (!funcionarioSelecionado) return;
    habilitarinputsF();
    acaoAtualF = 'editar';
    toggleBotoesF(true);
});

btnDeletarF.addEventListener('click', () => {
    if (!funcionarioSelecionado) return;
    acaoAtualF = 'deletar';
    toggleBotoesF(true);
});

// Event Listener para botão cancelar
btnCancelarF.addEventListener('click', () => {
    if (acaoAtualF === 'cadastrar') {
        limparinputsF();
    } else if (acaoAtualF === 'editar') {
        selecionarFuncionario(funcionarioSelecionado);
    }
    desabilitarinputsF();
    toggleBotoesF(false);
});

// Event Listener para botão confirmar
btnConfirmarF.addEventListener('click', async () => {
    try {
        let url = 'http://localhost:8080/as/employees';
        let method = 'POST';
        let body = {
            name: document.getElementById('nome').value,
            age: parseInt(document.getElementById('idade').value),
            cpf: document.getElementById('cpf').value,
            email: document.getElementById('email').value,
            telephone: document.getElementById('telefone').value,
            role: document.getElementById('cargo').value,
            accessLevel: parseInt(document.getElementById('acesso').value)
        };

        if (acaoAtualF === 'editar') {
            url += `/${funcionarioSelecionado.id}`;
            method = 'PUT';
        } else if (acaoAtualF === 'deletar') {
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
            limparinputsF();
            desabilitarinputsF();
            toggleBotoesF(false);
        } else {
            throw new Error('Erro na operação');
        }
    } catch (error) {
        console.error('Erro:', error);
    }
});

// Carregar funcionários ao iniciar
carregarFuncionarios();

