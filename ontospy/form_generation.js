
    function fetchIDs(){


    var getArray = document.getElementById('result');
    rawArray = getArray.innerHTML;
    console.log(rawArray);
    preArray = rawArray.split(',');
    var IDarray = preArray;
    IDarray[0] = preArray[0].split(':')[1].split(' ')[1];
    
    //console.log(preArray[0].split(':')[1]);
    console.log(IDarray);
    console.log(data);
    console.log(data.length);

    var result = []; 
    var i;
    var j;
   // console.log(data.id);
   /*for (j = 0 ; j < IDarray.length; j++)
   {
        console.log("inside outer loop\n");
        for(i = 0 ; i < data.length; i++)
        {
            console.log("inside inner loop\n");

            if (IDarray[j] == data[i].id){
                console.log("found for " + IDarray[i]+ " hurrah\n");
                break;
            }

        }   
}*/

    for(i = 0 ; i < data.length; i++ )
    {
        console.log(data[i].id);
    }


//    console.log(getObject(data, IDarray));
/*for (i = 0; i < IDarray.length; i++)
{
    var elementPos = data.map(function(x) {return x.id; }).indexOf(IDarray[i]);
}

console.log(elementPos);

var objectFound = data[elementPos];

console.log(objectFound);*/
//if(data[9].items)
//console.log(data[9].items[0]);

fetchIDs2(data, IDarray);

return;
    }


    var flag = 0;

function fetchIDs2(object, IDarray){

    if (object instanceof Array)
    {

        for(var i=0; i < object.length; i++ )
        {
            //console.log(object[i]);
            //console.log(object[i].id);
            //console.log(IDarray.length);
            for(var ind = 0; ind < IDarray.length; ind++)
            {
                if(object[i].id == IDarray[ind])
                {
                    //console.log("I found it\n");
                    console.log(IDarray[ind]+" : "+object[i].text);
                    var cover_box = document.createElement('div');
                    cover_box.id = '0';
                    cover_box.setAttribute("style","border:solid;border-color:rgb(100,100,100);");
                    //var target = document.getElementById("final_form");
                    var temp = document.createElement("input");
                    
                    if(flag == 0){
                        var addbtn = document.createElement("button");
                        addbtn.setAttribute("onclick","fetchIDs()");
                        //addbtn.value = 'Add';
                        //addbtn.setAttribute('id','addbtn');
                        addbtn.innerHTML = 'ADD more'
                        //target.appendChild(addbtn);
                        //target.innerHTML += "<br>"
                        flag = 1;
                    }
                    
                    temp.setAttribute("style", "margin-left:120px; display: block; box-sizing:border-box;");
                    target.innerHTML += object[i].text + "&nbsp;";
                    //document.setAttribute("type","text");
                    cover_box.appendChild(temp);
                    
                    //target.innerHTML += "<br><br>";
                    document.getElementsByTagName('body').appendChild(cover_box);
                    break;
                }

            }
            
            if(object[i].items)
                fetchIDs2(object[i].items, IDarray);
        }
    }

}