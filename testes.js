// Elementos do DOM
const testsList = document.getElementById('testsList');
const inputsT = document.querySelectorAll('.form-inputT');
const btnCadastrarT = document.querySelector('.btn-cadastrarT');
const btnEditarT = document.querySelector('.btn-editarT');
const btnConfirmarT = document.querySelector('.btn-confirmarT');
const btnCancelarT = document.querySelector('.btn-cancelarT');
const botoesPrincipaisT = document.querySelector('.botoes-principaisT');
const botoesConfirmacaoT = document.querySelector('.botoes-confirmacaoT');

// Estado da aplicação
let testSelecionado = null;
let acaoAtualT = null; // 'cadastrar' ou 'editar'

// Esconder botões de confirmação inicialmente
botoesConfirmacaoT.style.display = 'none';

// Desabilitar inputsT inicialmente
function desabilitarInputs() {
    inputsT.forEach(input => input.disabled = true);
}

// Habilitar inputsTT
function habilitarInputs() {
    inputsT.forEach(input => input.disabled = false);
}

// Inicializar com inputsT desabilitados
desabilitarInputs();

// Função para carregar tests
async function carregarTestes() {
    try {
        const response = await fetch('http://localhost:8080/as/tests');
        const tests = await response.json();
        
        testsList.innerHTML = '';
        tests.forEach(test => {
            const div = document.createElement('div');
            div.className = 'test-item';
            div.textContent = test.titulo;
            div.addEventListener('click', () => selecionarTeste(test));
            testsList.appendChild(div);
        });
    } catch (error) {
        console.error('Erro ao carregar tests:', error);
    }
}

// Função para selecionar test
function selecionarTeste(test) {
    testSelecionado = test;
    // Preencher inputsT com dados do test
    document.getElementById('camaraAtual').value = test.camaraAtual;
document.getElementById('titulo').value = test.titulo;
document.getElementById('objetivoTeste').value = test.objetivoTeste;
document.getElementById('descricaoTeste').value = test.descricaoTeste;
document.getElementById('anotacoes').value = test.anotacoes;
document.getElementById('quantidadeTestes').value = test.quantidadeTestes;
document.getElementById('baixas').value = test.baixas;
document.getElementById('resultadoAtual').value = test.resultadoAtual;

    // Manter inputsT desabilitados ao selecionar
    desabilitarInputs();
}

// Função para limpar inputsT
function limparInputs() {
    inputsT.forEach(input => input.value = '');
    testSelecionado = null;
}

// Função para mostrar/esconder botões
function toggleBotoesT(mostrarConfirmacao) {
    botoesPrincipaisT.style.display = mostrarConfirmacao ? 'none' : 'flex';
    botoesConfirmacaoT.style.display = mostrarConfirmacao ? 'flex' : 'none';
}

// Event Listeners para botões principais
btnCadastrarT.addEventListener('click', () => {
    limparInputs();
    habilitarInputs();
    acaoAtualT = 'cadastrar';
    toggleBotoesT(true);
});

btnEditarT.addEventListener('click', () => {
    if (!testSelecionado) return;
    habilitarInputs();
    acaoAtualT = 'editar';
    toggleBotoesT(true);
});

// Event Listener para botão cancelar
btnCancelarT.addEventListener('click', () => {
    if (acaoAtualT === 'cadastrar') {
        limparInputs();
    } else if (acaoAtualT === 'editar') {
        selecionarTeste(testSelecionado);
    }
    desabilitarInputs();
    toggleBotoesT(false);
});

// Event Listener para botão confirmar
btnConfirmarT.addEventListener('click', async () => {
    try {
        let url = 'http://localhost:8080/as/tests';
        let method = 'POST';
        let body = {
            camaraAtual: document.getElementById('camaraAtual').value, 
            titulo: document.getElementById('titulo').value, 
            objetivoTeste: document.getElementById('objetivoTeste').value, 
            descricaoTeste: document.getElementById('descricaoTeste').value,
             anotacoes: document.getElementById('anotacoes').value, 
             quantidadeTestes: parseInt(document.getElementById('quantidadeTestes').value), 
             resultadoAtual: document.getElementById('resultadoAtual').value, 
             baixas: parseInt(document.getElementById('baixas').value)
        };

        if (acaoAtualT === 'editar') {
            url += `/${testSelecionado.id}`;
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
            toggleBotoesT(false);
        } else {
            throw new Error('Erro na operação');
        }
    } catch (error) {
        console.error('Erro:', error);
    }
});

// Carregar tests ao iniciar
carregarTestes();