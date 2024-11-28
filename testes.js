// Elementos do DOM
const testesList = document.getElementById('testesList');
const inputsT = document.querySelectorAll('.form-inputT');
const btnCadastrarT = document.querySelector('.btn-cadastrarT');
const btnEditarT = document.querySelector('.btn-editarT');
const btnConfirmarT = document.querySelector('.btn-confirmarT');
const btnCancelarT = document.querySelector('.btn-cancelarT');
const botoesPrincipaisT = document.querySelector('.botoes-principaisT');
const botoesConfirmacaoT = document.querySelector('.botoes-confirmacaoT');

// Estado da aplicação
let testeSelecionado = null;
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

// Função para carregar testes
async function carregarTestes() {
    try {
        const response = await fetch('http://localhost:8080/as/testes');
        const testes = await response.json();
        
        testesList.innerHTML = '';
        testes.forEach(teste => {
            const div = document.createElement('div');
            div.className = 'teste-item';
            div.textContent = teste.titulo;
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
    // Preencher inputsT com dados do teste
    document.getElementById('camaraAtual').value = teste.camaraAtual;
document.getElementById('titulo').value = teste.titulo;
document.getElementById('objetivoTeste').value = teste.objetivoTeste;
document.getElementById('descricaoTeste').value = teste.descricaoTeste;
document.getElementById('anotacoes').value = teste.anotacoes;
document.getElementById('quantidadeTestes').value = teste.quantidadeTestes;
document.getElementById('baixas').value = teste.baixas;
document.getElementById('resultadoAtual').value = teste.resultadoAtual;

    // Manter inputsT desabilitados ao selecionar
    desabilitarInputs();
}

// Função para limpar inputsT
function limparInputs() {
    inputsT.forEach(input => input.value = '');
    testeSelecionado = null;
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
    if (!testeSelecionado) return;
    habilitarInputs();
    acaoAtualT = 'editar';
    toggleBotoesT(true);
});

// Event Listener para botão cancelar
btnCancelarT.addEventListener('click', () => {
    if (acaoAtualT === 'cadastrar') {
        limparInputs();
    } else if (acaoAtualT === 'editar') {
        selecionarTeste(testeSelecionado);
    }
    desabilitarInputs();
    toggleBotoesT(false);
});

// Event Listener para botão confirmar
btnConfirmarT.addEventListener('click', async () => {
    try {
        let url = 'http://localhost:8080/as/testes';
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
            toggleBotoesT(false);
        } else {
            throw new Error('Erro na operação');
        }
    } catch (error) {
        console.error('Erro:', error);
    }
});

// Carregar testes ao iniciar
carregarTestes();