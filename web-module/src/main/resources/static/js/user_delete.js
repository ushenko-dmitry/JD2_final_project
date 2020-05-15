var delete_checkbox_id_text;
var elements;
window.onload = function(){
    delete_checkbox_id_text = "delete_checkbox_";

    elements = document.getElementsByName("delete_checkbox");
    for (var i = 0; i < elements.length; i++) {
        elements[i].addEventListener("click", delete_user_manager);
    }
}

function delete_user_manager() {
    var input_pattern = "<input type='hidden' name='deleteCheckbox' value='<ID>'/>";
    var delete_inputs = document.getElementById("delete_inputs");
    var inputs = "";
    for (var i = 0; i < elements.length; i++) {
        var id = elements[i].attributes["id"].value.substring(delete_checkbox_id_text.length);
        if (elements[i].checked){
            inputs += input_pattern.replace("<ID>", id);
        }
    }
    delete_inputs.innerHTML = inputs;
}