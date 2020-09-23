window.onload=function(){    
    var lista = document.getElementById('lista');
    var email = document.getElementById('email');
    lista.addEventListener('change', ()=>{
        if(lista.value == "ADMINISTRATORS"){
            email.setAttribute("type", "text");
            email.setAttribute("placeholder", "Código de administrador");
        }
        else{
            email.setAttribute("type", "email");
            email.setAttribute("placeholder", "Email");
        }
    });
};

function validarFormulario(formulario) {
    var user = formulario.email;
    var pass = formulario.pass;
    
    if(user.value == "") {
        alert("Debe proporcionar un email");
        user.focus();
        user.select();
        return false;
    }
    
    if(pass.value == "") {
        alert("Debe proporcionar una contraseña");
        pass.focus();
        pass.select();
        return false;
    }
    
    return true;
}