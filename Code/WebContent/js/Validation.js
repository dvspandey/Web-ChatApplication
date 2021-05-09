function validate(frm){

  //empty the existing form validation error messages
    document.getElementById("indicator").innerHTML="";



    //read form data
    let  user=frm.user.value;
    let flag=true;
    
    
    //----Client side form validations ---------
    if(user==""){
      flag=false;
      document.getElementById("indicator").innerHTML="Please Selcet AnyOne*"
      //frm.user.focus();
    }

    
    return flag;
}
