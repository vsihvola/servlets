
async function removeProduct(id) {
    let res = await fetch('/list' + `?id=${id}`, 
 		   {method: 'DELETE'}) 
    .then(res => {
    	var rowId = "product-" +  id;
    	var myObj = document.getElementById(rowId);
    	myObj.remove();

    }) 
   	
}

async function updateProduct(title){
	
}

