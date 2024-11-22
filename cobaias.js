// Array para armazenar os cobaias
let currentActionC = null; 
let selectedCobaiaId = null; // Variável global para armazenar o ID da cobaia selecionada

// Função para carregar cobaias do banco de dados
function loadCobaiasFromDatabase() { 
    fetch('http://localhost:8080/as/cobaias') 
    .then(response => response.json()) 
    .then(data => { 
        cobaias = data; // Supondo que a resposta seja uma lista de cobaias
        updateCobaiasList(); 
    }) 
    .catch(error => { 
        console.error('Erro ao carregar cobaias:', error); 
        alert('Ocorreu um erro ao carregar as cobaias.'); 
    }); 
}

// Função para atualizar a lista de cobaias
function updateCobaiasList() { 
    const container = document.getElementById('cobaiasList'); 
    container.innerHTML = ''; 
    cobaias.forEach(cob => { 
        const div = document.createElement('div'); 
        div.className = 'cobaia-item'; 
        div.dataset.id = cob.id; 
        div.textContent = cob.nome; 
        div.onclick = () => { 
            // Remove seleção anterior
            document.querySelectorAll('.cobaia-item').forEach(item => { 
                item.classList.remove('selected'); 
            }); 
            // Adiciona seleção ao item clicado
            div.classList.add('selected'); 
            selectCobaia(cob.id); 
        }; 
        container.appendChild(div); 
    }); 
}

// Função para selecionar uma cobaia
function selectCobaia(id) { 
    const cobaia = cobaias.find(c => c.id == id); 
    if (cobaia) { 
        selectedCobaiaId = cobaia.id; // Atualiza o ID da cobaia selecionada
        document.getElementById('nomeCobaias').value = cobaia.nome; 
        document.getElementById('idadeCobaias').value = cobaia.idade; 
        document.getElementById('alturaCobaias').value = cobaia.altura; 
        document.getElementById('tipoSanguineoCobaias').value = cobaia.tipoSanguineo; 
        document.getElementById('qiCobaias').value = cobaia.qi; 
        document.getElementById('formacaoAcademicaCobaias').value = cobaia.formacaoAcademica; 
        document.getElementById('resultadoTesteResistenciaCobaias').value = cobaia.resultadoTesteResistencia; 
        document.getElementById('resultadoTesteForcaCobaias').value = cobaia.resultadoTesteForca; 
        document.getElementById('resultadoTesteVelocidadeCobaias').value = cobaia.resultadoTesteVelocidade;  
    } 
}

// Função para limpar o formulário
function clearForm() { 
    document.getElementById('nomeCobaias').value = ''; 
    document.getElementById('idadeCobaias').value = ''; 
    document.getElementById('alturaCobaias').value = ''; 
    document.getElementById('tipoSanguineoCobaias').value = ''; 
    document.getElementById('qiCobaias').value = '';  
    document.getElementById('formacaoAcademicaCobaias').value = '';  
    document.getElementById('resultadoTesteResistenciaCobaias').value = '';  
    document.getElementById('resultadoTesteForcaCobaias').value = '';  
    document.getElementById('resultadoTesteVelocidadeCobaias').value = '';  
    selectedCobaiaId = null; // Limpa o ID da cobaia selecionada
}

// Função para verificar se todos os campos estão preenchidos
function validateForm() { 
    const inputs = document.querySelectorAll('.form-inputC'); // Alterado para 'C'
    return Array.from(inputs).every(input => input.value.trim() !== ''); 
}

// Desabilitar inputs inicialmente
document.querySelectorAll('.form-inputC').forEach(input => { // Alterado para 'C'
    input.disabled = true; 
}); 

// Esconder botões de confirmação inicialmente
document.querySelector('.botoes-confirmacaoC').style.display = 'none'; // Alterado para 'C'

// Event Listeners para os botões principais
document.querySelector('.btn-editarC').addEventListener('click', function() { // Alterado para 'C'
    if (!selectedCobaiaId) { // Alterado para 'C'
        alert('Selecione uma cobaia para editar'); return; 
    } 

    currentActionC = 'edit'; 

    document.querySelectorAll('.form-inputC').forEach(input => { // Alterado para 'C'
        input.disabled = false; 
    }); 

    document.querySelector('.botoes-principaisC').style.display = 'none'; // Alterado para 'C'
    document.querySelector('.botoes-confirmacaoC').style.display = 'flex'; // Alterado para 'C'
});

document.querySelector('.btn-deletarC').addEventListener('click', function() { // Alterado para 'C'
    if (!selectedCobaiaId) { // Alterado para 'C'
        alert('Selecione uma cobaia para deletar'); return;  
    } 

    currentActionC = 'delete'; 

    document.querySelector('.botoes-principaisC').style.display = 'none'; // Alterado para 'C'
    document.querySelector('.botoes-confirmacaoC').style.display = 'flex'; // Alterado para 'C'
});

document.querySelector('.btn-cadastrarC').addEventListener('click', function() { // Alterado para 'C'
    currentActionC = 'create'; clearForm(); 

    // Habilitar inputs para cadastro
    document.querySelectorAll('.form-inputC').forEach(input => { // Alterado para 'C'
        input.disabled = false;  
    }); 

    // Ocultar botões principais e mostrar confirmação
    document.querySelector('.botoes-principaisC').style.display = 'none'; // Alterado para 'C'
    document.querySelector('.botoes-confirmacaoC').style.display = 'flex'; // Alterado para 'C'
});

// Event Listener para botão cancelar
document.querySelector('.btn-cancelarC').addEventListener('click', function() { // Alterado para 'C'
    clearForm(); 

    // Desabilitar inputs e mostrar botões principais novamente
    document.querySelectorAll('.form-inputC').forEach(input => { // Alterado para 'C'
        input.disabled = true;  
    }); 

    // Exibir botões principais novamente
    document.querySelector('.botoes-principaisC').style.display = 'flex'; // Alterado para 'C'
    document.querySelector('.botoes-confirmacaoC').style.display = 'none'; // Alterado para 'C'

    currentActionC = null;  
});

// Event Listener para botão confirmar
document.querySelector('.btn-confirmarC').addEventListener('click', function() { // Alterado para 'C'
    
   if (currentActionC === 'delete') {
       if (!selectedCobaiaId) {
           alert('Nenhuma cobaia selecionada para deletar.'); return;
       }

       // Enviar requisição DELETE para o servidor
       fetch(`http://localhost:8080/as/cobaias/${selectedCobaiaId}`, {
           method: 'DELETE',
           headers: {
               'Content-Type': 'application/json',
           }
       })
       .then(response => {
           if (response.ok) {
               // Remover a cobaia da lista localmente e recarregar a lista do banco de dados
               cobaias = cobaias.filter(c => c.id !== selectedCobaiaId);
               loadCobaiasFromDatabase(); clearForm();
               alert('Cobaia deletada com sucesso.');
           } else {
               throw new Error('Erro ao deletar a cobaia.');
           }
       })
       .catch(error => {
           console.error('Erro ao deletar a cobaia:', error);
           alert('Ocorreu um erro ao deletar a cobaia.');
       });
   } else if (currentActionC === 'create' || currentActionC === 'edit') {
       if (!validateForm()) {
           alert('Por favor, preencha todos os campos'); return;
       }

       if (currentActionC === 'create') {
           // Criar nova cobaia
           const newCobaia = {
               nome: document.getElementById('nomeCobaias').value,
               idade: parseInt(document.getElementById('idadeCobaias').value),
               altura: document.getElementById('alturaCobaias').value,
               tipoSanguineo: document.getElementById('tipoSanguineoCobaias').value,
               qi: parseInt(document.getElementById('qiCobaias').value),
               formacaoAcademica: document.getElementById('formacaoAcademicaCobaias').value,
               resultadoTesteResistencia: parseInt(document.getElementById('resultadoTesteResistenciaCobaias').value),
               resultadoTesteForca: parseInt(document.getElementById('resultadoTesteForcaCobaias').value),
               resultadoTesteVelocidade: parseInt(document.getElementById('resultadoTesteVelocidadeCobaias').value)
           };

           // Enviar cobaia ao servidor
           fetch('http://localhost:8080/as/cobaias', {
               method: 'POST',
               headers: {
                   'Content-Type': 'application/json',
               },
               body: JSON.stringify(newCobaia),
           })
           .then(response => response.json())
           .then(data => {
               // Recarregar todas as cobaias do banco de dados
               loadCobaiasFromDatabase(); clearForm();
           })
           .catch(error => {
               console.error('Erro ao cadastrar a cobaia:', error);
               alert('Ocorreu um erro ao cadastrar a cobaia.');
           });
       } else if (currentActionC === 'edit') {
           const updatedCobaia = {
               nome: document.getElementById('nomeCobaias').value,
               idade: parseInt(document.getElementById('idadeCobaias').value),
               altura: document.getElementById('alturaCobaias').value,
               tipoSanguineo: document.getElementById('tipoSanguineoCobaias').value,
               qi: parseInt(document.getElementById('qiCobaias').value),
               formacaoAcademica: document.getElementById('formacaoAcademicaCobaias').value,
               resultadoTesteResistencia: parseInt(document.getElementById('resultadoTesteResistenciaCobaias').value),
               resultadoTesteForca: parseInt(document.getElementById('resultadoTesteForcaCobaias').value),
               resultadoTesteVelocidade: parseInt(document.getElementById('resultadoTesteVelocidadeCobaias').value)
           };

           // Enviar cobaia atualizada ao servidor
           fetch(`http://localhost:8080/as/cobaias/${selectedCobaiaId}`, {
               method: 'PUT',
               headers: {
                   'Content-Type': 'application/json',
               },
               body: JSON.stringify(updatedCobaia),
           })
           .then(response => response.json())
           .then(data => {
               // Recarregar todas as cobaias do banco de dados
               loadCobaiasFromDatabase(); clearForm();
           })
           .catch(error => {
               console.error('Erro ao atualizar a cobaia:', error);
               alert('Ocorreu um erro ao atualizar a cobaia.');
           });
       }
   }

   // Atualizar a lista e limpar o estado
   loadCobaiasFromDatabase(); clearForm();

   // Desabilitar inputs e mostrar botões principais novamente
   document.querySelectorAll('.form-inputC').forEach(input => {  // Alterado para 'C'
       input.disabled = true;  
   });
   document.querySelector('.botoes-principaisC').style.display = 'flex';  // Alterado para 'C'
   document.querySelector('.botoes-confirmacaoC').style.display = 'none';  // Alterado para 'C'

   currentActionC = null;
});

// Inicializar a lista de cobaias ao carregar a página
document.addEventListener('DOMContentLoaded', loadCobaiasFromDatabase);