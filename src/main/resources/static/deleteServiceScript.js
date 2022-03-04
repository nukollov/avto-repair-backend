let id = NaN

function setId(idNew){
    id = idNew;
}

function deleteService(){
    document.location.href='deleteService/id=' + id
}