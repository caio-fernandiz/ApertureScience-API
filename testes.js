// Elementos do DOM
const TestsList = document.getElementById('TestsList');
const inputsT = document.querySelectorAll('.form-inputT');
const btnCadastrarT = document.querySelector('.btn-cadastrarT');
const btnEditarT = document.querySelector('.btn-editarT');
const btnConfirmarT = document.querySelector('.btn-confirmarT');
const btnCancelarT = document.querySelector('.btn-cancelarT');
const botoesPrincipaisT = document.querySelector('.botoes-principaisT');
const botoesConfirmacaoT = document.querySelector('.botoes-confirmacaoT');

// Estado da aplicação
let TestSelecionado = null;
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

// Função para carregar Tests
async function carregarTests() {
    try {
        const response = await fetch('http://localhost:8080/as/Tests');
        const Tests = await response.json();
        
        TestsList.innerHTML = '';
        Tests.forEach(Test => {
            const div = document.createElement('div');
            div.classusername = 'Test-item';
            div.textContent = Test.title;
            div.addEventListener('click', () => selecionarTest(Test));
            TestsList.appendChild(div);
        });
    } catch (error) {
        console.error('Erro ao carregar Tests:', error);
    }
}

// Função para selecionar Test
function selecionarTest(Test) {
    TestSelecionado = Test;
    // Preencher inputsT com dados do Test
    document.getElementById('currentChamber').value = Test.currentChamber;
document.getElementById('title').value = Test.title;
document.getElementById('testObjective').value = Test.testObjective;
document.getElementById('testDescription').value = Test.testDescription;
document.getElementById('notes').value = Test.notes;
document.getElementById('testQuantity').value = Test.testQuantity;
document.getElementById('downs').value = Test.downs;
document.getElementById('currentResult').value = Test.currentResult;

    // Manter inputsT desabilitados ao selecionar
    desabilitarInputs();
}

// Função para limpar inputsT
function limparInputs() {
    inputsT.forEach(input => input.value = '');
    TestSelecionado = null;
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
    if (!TestSelecionado) return;
    habilitarInputs();
    acaoAtualT = 'editar';
    toggleBotoesT(true);
});

// Event Listener para botão cancelar
btnCancelarT.addEventListener('click', () => {
    if (acaoAtualT === 'cadastrar') {
        limparInputs();
    } else if (acaoAtualT === 'editar') {
        selecionarTest(TestSelecionado);
    }
    desabilitarInputs();
    toggleBotoesT(false);
});

// Event Listener para botão confirmar
btnConfirmarT.addEventListener('click', async () => {
    try {
        let url = 'http://localhost:8080/as/Tests';
        let method = 'POST';
        let body = {
            currentChamber: document.getElementById('currentChamber').value, 
            title: document.getElementById('title').value, 
            testObjective: document.getElementById('testObjective').value, 
            testDescription: document.getElementById('testDescription').value,
             notes: document.getElementById('notes').value, 
             testQuantity: parseInt(document.getElementById('testQuantity').value), 
             currentResult: document.getElementById('currentResult').value, 
             downs: parseInt(document.getElementById('downs').value)
        };

        if (acaoAtualT === 'editar') {
            url += `/${TestSelecionado.id}`;
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
            await carregarTests();
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

// Carregar Tests ao iniciar
carregarTests();