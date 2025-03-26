// Elementos do DOM
const GuinneaPigsList = document.getElementById('GuinneaPigsList');
const inputsC = document.querySelectorAll('.form-inputC');
const btnCadastrarC = document.querySelector('.btn-cadastrarC');
const btnEditarC = document.querySelector('.btn-editarC');
const btnDeletarC = document.querySelector('.btn-deletarC');
const btnConfirmarC = document.querySelector('.btn-confirmarC');
const btnCancelarC = document.querySelector('.btn-cancelarC');
const botoesPrincipaisC = document.querySelector('.botoes-principaisC');
const botoesConfirmacaoC = document.querySelector('.botoes-confirmacaoC');
// Estado da aplicação
let GuinneaPigSelecionada = null;
let acaoAtualC = null; // 'cadastrar', 'editar' ou 'deletar'
// Esconder botões de confirmação inicialmente
botoesConfirmacaoC.style.display = 'none';
// Desabilitar inputsC inicialmente
function desabilitarinputsC() {
    inputsC.forEach(input => input.disabled = true);
}
// Habilitar inputsC
function habilitarinputsC() {
    inputsC.forEach(input => input.disabled = false);
}
// Inicializar com inputsC desabilitados
desabilitarinputsC();
// Função para carregar GuinneaPigs
async function carregarGuinneaPigs() {
    try {
        const response = await fetch('http://localhost:8080/as/GuinneaPigs');
        const GuinneaPigs = await response.json();
        
        GuinneaPigsList.innerHTML = '';
        GuinneaPigs.forEach(GuinneaPig => {
            const div = document.createElement('div');
            div.classusername = 'GuinneaPig-item';
            div.textContent = GuinneaPig.username;
            div.addEventListener('click', () => selecionarGuinneaPig(GuinneaPig));
            GuinneaPigsList.appendChild(div);
        });
    } catch (error) {
        console.error('Erro ao carregar GuinneaPigs:', error);
    }
}
// Função para selecionar GuinneaPig
function selecionarGuinneaPig(GuinneaPig) {
    GuinneaPigSelecionada = GuinneaPig;
    // Preencher inputsC com dados da GuinneaPig
    document.getElementById('usernameGuinneaPigs').value = GuinneaPig.username;
    document.getElementById('ageGuinneaPigs').value = GuinneaPig.age;
    document.getElementById('qiGuinneaPigs').value = GuinneaPig.qi;
    document.getElementById('cpfGuinneaPigs').value = GuinneaPig.cpf;
    document.getElementById('heightGuinneaPigs').value = GuinneaPig.height;
    document.getElementById('bloodTypeGuinneaPigs').value = GuinneaPig.bloodType;
    document.getElementById('academicBackgroundGuinneaPigs').value = GuinneaPig.academicBackground;
    document.getElementById('resistanceTestResultGuinneaPigs').value = GuinneaPig.resistanceTestResult;
    document.getElementById('forceTestResultGuinneaPigs').value = GuinneaPig.forceTestResult;
    document.getElementById('speedTestResultGuinneaPigs').value = GuinneaPig.speedTestResult;
    // Manter inputsC desabilitados ao selecionar
    desabilitarinputsC();
}
// Função para limpar inputsC
function limparinputsC() {
    inputsC.forEach(input => input.value = '');
    GuinneaPigSelecionada = null;
}
// Função para mostrar/esconder botões
function toggleBotoesC(mostrarConfirmacao) {
    botoesPrincipaisC.style.display = mostrarConfirmacao ? 'none' : 'flex';
    botoesConfirmacaoC.style.display = mostrarConfirmacao ? 'flex' : 'none';
}
// Event Listeners para botões principais
btnCadastrarC.addEventListener('click', () => {
    limparinputsC();
    habilitarinputsC();
    acaoAtualC = 'cadastrar';
    toggleBotoesC(true);
});
btnEditarC.addEventListener('click', () => {
    if (!GuinneaPigSelecionada) return;
    habilitarinputsC();
    acaoAtualC = 'editar';
    toggleBotoesC(true);
});
btnDeletarC.addEventListener('click', () => {
    if (!GuinneaPigSelecionada) return;
    acaoAtualC = 'deletar';
    toggleBotoesC(true);
});
// Event Listener para botão cancelar
btnCancelarC.addEventListener('click', () => {
    if (acaoAtualC === 'cadastrar') {
        limparinputsC();
    } else if (acaoAtualC === 'editar') {
        selecionarGuinneaPig(GuinneaPigSelecionada);
    }
    desabilitarinputsC();
    toggleBotoesC(false);
});
// Event Listener para botão confirmar
btnConfirmarC.addEventListener('click', async () => {
    try {
        let url = 'http://localhost:8080/as/GuinneaPigs';
        let method = 'POST';
        let body = {
            username: document.getElementById('usernameGuinneaPigs').value,
            age: parseInt(document.getElementById('ageGuinneaPigs').value),
            qi: parseInt(document.getElementById('qiGuinneaPigs').value),
            cpf: document.getElementById('cpfGuinneaPigs').value,
            height: document.getElementById('heightGuinneaPigs').value,
            bloodType: document.getElementById('bloodTypeGuinneaPigs').value,
            academicBackground: document.getElementById('academicBackgroundGuinneaPigs').value,
            resistanceTestResult: parseInt(document.getElementById('resistanceTestResultGuinneaPigs').value),
            forceTestResult: parseInt(document.getElementById('forceTestResultGuinneaPigs').value),
            speedTestResult: parseInt(document.getElementById('speedTestResultGuinneaPigs').value)
        };
        if (acaoAtualC === 'editar') {
            url += `/${GuinneaPigSelecionada.id}`;
            method = 'PUT';
        } else if (acaoAtualC === 'deletar') {
            url += `/${GuinneaPigSelecionada.id}`;
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
            await carregarGuinneaPigs();
            limparinputsC();
            desabilitarinputsC();
            toggleBotoesC(false);
        } else {
            throw new Error('Erro na operação');
        }
    } catch (error) {
        console.error('Erro:', error);
    }
});
// Carregar GuinneaPigs ao iniciar
carregarGuinneaPigs();