function deleteGroup(groupReference, numberOfGroups){
    if(numberOfGroups > 1){
        $(groupReference).remove();
    }else{
        cleanFields(groupReference);
    }
}

function cleanFields(address){
    $(address).find('input').each(
        function(i,v){
            if($(v).is(":visible")){
                $(v).val('');
            }
        }
    );
    $(address).find('select').each(
        function(i,v){
            $(v).val(-1);
        }
    );
}

function nextIndex(aClass){
    var busyIndexes = new Array();
    $(aClass).each(
        function (i,v){
            var test = $(v).find('select, input, textarea')[0].name;
            var currentIndex = parseInt(test.substring(test.indexOf('[')+1,test.indexOf(']')));
            busyIndexes.push(currentIndex);
        }
    );

    var i = 0;
    var shouldContinue = true;
    for(i; i< busyIndexes.length ; i++){
        for(var j = 0 ; j<busyIndexes.length; j++){
            if(i == busyIndexes[j]){
                break;
            }

            if( j+1 == busyIndexes.length ){
                return i;
            }
        }
    }
    return i;
}

function nextStudyCoordinatorIndex(groupSelector){
    return nextIndex(groupSelector);
}

function defaultAddRow(groupSelector, numberOfGroups){
    var newSC;
    if(true){
        if($($(groupSelector)[0]).is(':visible')){
            //si se puede ver entonces agrego
            var lastSC = $(groupSelector)[numberOfGroups-1];
            newSC = $(lastSC).clone()[0];

            //blanquear campos
            cleanFields(newSC);

            //cambiar indices de cada atributo name
            var indexForName = nextStudyCoordinatorIndex(groupSelector);
            $(newSC).find('select, input, textarea').each(
                function(i,v){
                    var test = v.name;
                    var firtsPart = test.substring(0,test.indexOf('[')+1);
                    var lastPart = test.substring(test.indexOf(']'),test.length);
                    v.name = firtsPart + indexForName + lastPart;
                }
            );

            $(lastSC).after(newSC);
        }else{
            //si no se puede ver entonces lo muestro
            $($(groupSelector)[0]).show();
            newSC = $(groupSelector)[0];
        }
    }
    $($(groupSelector)[0]).show();
    return newSC;
}


function neutralizePositive(aValue){
    return Number((aValue >=0 || isNaN(aValue))? 0 : aValue);
}

function printError(title,message,print){
    if(print)
        alert(title+": "+message);
}



function disableGroups(groupSelector){
    $(groupSelector).each(
        function(){
            $(this).hide();
        }
    );
}

function enableGroups(groupSelector){
    $(groupSelector).each(
        function(){
            $(this).show();
        }
    );
}