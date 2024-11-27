// Elementos do DOM
const cobaiasList = document.getElementById('cobaiasList');
const inputsC = document.querySelectorAll('.form-inputC');
const btnCadastrarC = document.querySelector('.btn-cadastrarC');
const btnEditarC = document.querySelector('.btn-editarC');
const btnDeletarC = document.querySelector('.btn-deletarC');
const btnConfirmarC = document.querySelector('.btn-confirmarC');
const btnCancelarC = document.querySelector('.btn-cancelarC');
const botoesPrincipaisC = document.querySelector('.botoes-principaisC');
const botoesConfirmacaoC = document.querySelector('.botoes-confirmacaoC');
// Estado da aplicação
let cobaiaSelecionada = null;
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
// Função para carregar cobaias
async function carregarCobaias() {
    try {
        const response = await fetch('http://localhost:8080/as/cobaias');
        const cobaias = await response.json();
        
        cobaiasList.innerHTML = '';
        cobaias.forEach(cobaia => {
            const div = document.createElement('div');
            div.className = 'cobaia-item';
            div.textContent = cobaia.nome;
            div.addEventListener('click', () => selecionarCobaia(cobaia));
            cobaiasList.appendChild(div);
        });
    } catch (error) {
        console.error('Erro ao carregar cobaias:', error);
    }
}
// Função para selecionar cobaia
function selecionarCobaia(cobaia) {
    cobaiaSelecionada = cobaia;
    // Preencher inputsC com dados da cobaia
    document.getElementById('nomeCobaias').value = cobaia.nome;
    document.getElementById('idadeCobaias').value = cobaia.idade;
    document.getElementById('qiCobaias').value = cobaia.qi;
    document.getElementById('cpfCobaias').value = cobaia.cpf;
    document.getElementById('alturaCobaias').value = cobaia.altura;
    document.getElementById('tipoSanguineoCobaias').value = cobaia.tipoSanguineo;
    document.getElementById('formacaoAcademicaCobaias').value = cobaia.formacaoAcademica;
    document.getElementById('resultadoTesteResistenciaCobaias').value = cobaia.resultadoTesteResistencia;
    document.getElementById('resultadoTesteForcaCobaias').value = cobaia.resultadoTesteForca;
    document.getElementById('resultadoTesteVelocidadeCobaias').value = cobaia.resultadoTesteVelocidade;
    // Manter inputsC desabilitados ao selecionar
    desabilitarinputsC();
}
// Função para limpar inputsC
function limparinputsC() {
    inputsC.forEach(input => input.value = '');
    cobaiaSelecionada = null;
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
    if (!cobaiaSelecionada) return;
    habilitarinputsC();
    acaoAtualC = 'editar';
    toggleBotoesC(true);
});
btnDeletarC.addEventListener('click', () => {
    if (!cobaiaSelecionada) return;
    acaoAtualC = 'deletar';
    toggleBotoesC(true);
});
// Event Listener para botão cancelar
btnCancelarC.addEventListener('click', () => {
    if (acaoAtualC === 'cadastrar') {
        limparinputsC();
    } else if (acaoAtualC === 'editar') {
        selecionarCobaia(cobaiaSelecionada);
    }
    desabilitarinputsC();
    toggleBotoesC(false);
});
// Event Listener para botão confirmar
btnConfirmarC.addEventListener('click', async () => {
    try {
        let url = 'http://localhost:8080/as/cobaias';
        let method = 'POST';
        let body = {
            nome: document.getElementById('nomeCobaias').value,
            idade: parseInt(document.getElementById('idadeCobaias').value),
            qi: parseInt(document.getElementById('qiCobaias').value),
            cpf: document.getElementById('cpfCobaias').value,
            altura: document.getElementById('alturaCobaias').value,
            tipoSanguineo: document.getElementById('tipoSanguineoCobaias').value,
            formacaoAcademica: document.getElementById('formacaoAcademicaCobaias').value,
            resultadoTesteResistencia: parseInt(document.getElementById('resultadoTesteResistenciaCobaias').value),
            resultadoTesteForca: parseInt(document.getElementById('resultadoTesteForcaCobaias').value),
            resultadoTesteVelocidade: parseInt(document.getElementById('resultadoTesteVelocidadeCobaias').value)
        };
        if (acaoAtualC === 'editar') {
            url += `/${cobaiaSelecionada.id}`;
            method = 'PUT';
        } else if (acaoAtualC === 'deletar') {
            url += `/${cobaiaSelecionada.id}`;
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
            await carregarCobaias();
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
// Carregar cobaias ao iniciar
carregarCobaias();