// Elementos do DOM
const testesList = document.getElementById('testesList');
const inputs = document.querySelectorAll('.form-inputT');
const btnCadastrar = document.querySelector('.btn-cadastrarT');
const btnEditar = document.querySelector('.btn-editarT');
const btnConfirmar = document.querySelector('.btn-confirmarT');
const btnCancelar = document.querySelector('.btn-cancelarT');
const botoesPrincipais = document.querySelector('.botoes-principaisT');
const botoesConfirmacao = document.querySelector('.botoes-confirmacaoT');

// Estado da aplicação
let testeSelecionado = null;
let acaoAtual = null; // 'cadastrar' ou 'editar'

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

// Função para carregar testes
async function carregarTestes() {
    try {
        const response = await fetch('http://localhost:8080/as/testes');
        const testes = await response.json();
        
        testesList.innerHTML = '';
        testes.forEach(teste => {
            const div = document.createElement('div');
            div.className = 'teste-item';
            div.textContent = teste.nome;
            div.addEventListener('click', () => selecionarTeste(teste));
            testesList.appendChild(div);
        });
    } catch (error) {
        console.error('Erro ao carregar testes:', error);
    }
}

// Função para selecionar teste
function selecionarTeste(teste) {
    testeSelecionado = teste;
    // Preencher inputs com dados do teste
    document.getElementById('nomeTeste').value = teste.nome;
    document.getElementById('descricaoTeste').value = teste.descricao;
    document.getElementById('tipoTeste').value = teste.tipo;
    document.getElementById('duracaoTeste').value = teste.duracao;
    document.getElementById('pontuacaoMaxima').value = teste.pontuacaoMaxima;
    // Manter inputs desabilitados ao selecionar
    desabilitarInputs();
}

// Função para limpar inputs
function limparInputs() {
    inputs.forEach(input => input.value = '');
    testeSelecionado = null;
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
    if (!testeSelecionado) return;
    habilitarInputs();
    acaoAtual = 'editar';
    toggleBotoes(true);
});

// Event Listener para botão cancelar
btnCancelar.addEventListener('click', () => {
    if (acaoAtual === 'cadastrar') {
        limparInputs();
    } else if (acaoAtual === 'editar') {
        selecionarTeste(testeSelecionado);
    }
    desabilitarInputs();
    toggleBotoes(false);
});

// Event Listener para botão confirmar
btnConfirmar.addEventListener('click', async () => {
    try {
        let url = 'http://localhost:8080/as/testes';
        let method = 'POST';
        let body = {
            nome: document.getElementById('nomeTeste').value,
            descricao: document.getElementById('descricaoTeste').value,
            tipo: document.getElementById('tipoTeste').value,
            duracao: parseInt(document.getElementById('duracaoTeste').value),
            pontuacaoMaxima: parseInt(document.getElementById('pontuacaoMaxima').value)
        };

        if (acaoAtual === 'editar') {
            url += `/${testeSelecionado.id}`;
            method = 'PUT';
        }

        const response = await fetch(url, {
            method: method,
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(body)
        });

        if (response.ok) {
            await carregarTestes();
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

// Carregar testes ao iniciar
carregarTestes();