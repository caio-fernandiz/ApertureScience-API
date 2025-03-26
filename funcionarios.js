// Elementos do DOM
const EmployeesList = document.getElementById('EmployeesList');
const inputsF = document.querySelectorAll('.form-inputF');
const btnCadastrarF = document.querySelector('.btn-cadastrarF');
const btnEditarF = document.querySelector('.btn-editarF');
const btnDeletarF = document.querySelector('.btn-deletarF');
const btnConfirmarF = document.querySelector('.btn-confirmarF');
const btnCancelarF = document.querySelector('.btn-cancelarF');
const botoesPrincipaisF = document.querySelector('.botoes-principaisF');
const botoesConfirmacaoF = document.querySelector('.botoes-confirmacaoF');

// Estado da aplicação
let EmployeeSelecionado = null;
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
async function carregarEmployees() {
    try {
        const response = await fetch('http://localhost:8080/as/Employees');
        const Employees = await response.json();
        
        EmployeesList.innerHTML = '';
        Employees.forEach(Employee => {
            const div = document.createElement('div');
            div.classusername = 'Employee-item';
            div.textContent = Employee.username;
            div.addEventListener('click', () => selecionarEmployee(Employee));
            EmployeesList.appendChild(div);
        });
    } catch (error) {
        console.error('Erro ao carregar funcionários:', error);
    }
}

// Função para selecionar funcionário
function selecionarEmployee(Employee) {
    EmployeeSelecionado = Employee;
    // Preencher inputsF com dados do funcionário
    document.getElementById('username').value = Employee.username;
    document.getElementById('age').value = Employee.age;
    document.getElementById('cpf').value = Employee.cpf;
    document.getElementById('email').value = Employee.email;
    document.getElementById('telephone').value = Employee.telephone;
    document.getElementById('role').value = Employee.role;
    document.getElementById('acesso').value = Employee.acessLvl;
    // Manter inputsF desabilitados ao selecionar
    desabilitarinputsF();
}

// Função para limpar inputsF
function limparinputsF() {
    inputsF.forEach(input => input.value = '');
    EmployeeSelecionado = null;
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
    if (!EmployeeSelecionado) return;
    habilitarinputsF();
    acaoAtualF = 'editar';
    toggleBotoesF(true);
});

btnDeletarF.addEventListener('click', () => {
    if (!EmployeeSelecionado) return;
    acaoAtualF = 'deletar';
    toggleBotoesF(true);
});

// Event Listener para botão cancelar
btnCancelarF.addEventListener('click', () => {
    if (acaoAtualF === 'cadastrar') {
        limparinputsF();
    } else if (acaoAtualF === 'editar') {
        selecionarEmployee(EmployeeSelecionado);
    }
    desabilitarinputsF();
    toggleBotoesF(false);
});

// Event Listener para botão confirmar
btnConfirmarF.addEventListener('click', async () => {
    try {
        let url = 'http://localhost:8080/as/Employees';
        let method = 'POST';
        let body = {
            username: document.getElementById('username').value,
            age: parseInt(document.getElementById('age').value),
            cpf: document.getElementById('cpf').value,
            email: document.getElementById('email').value,
            telephone: document.getElementById('telephone').value,
            role: document.getElementById('role').value,
            acessLvl: parseInt(document.getElementById('acesso').value)
        };

        if (acaoAtualF === 'editar') {
            url += `/${EmployeeSelecionado.id}`;
            method = 'PUT';
        } else if (acaoAtualF === 'deletar') {
            url += `/${EmployeeSelecionado.id}`;
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
            await carregarEmployees();
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
carregarEmployees();

