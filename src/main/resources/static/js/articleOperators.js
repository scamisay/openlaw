var articleGroup =".articleGroup";
function numberOfArticleGroup(){
    return $(articleGroup).length;
}

function updateArticleNumber(newSC){
    var articleNumber = numberOfArticleGroup();
    $(newSC).find('.articleNumberText').text('Articulo '+ articleNumber);
    $(newSC).find('.articleNumber').val(articleNumber);
}

function reLabelArticles(){
    $(articleGroup).each(function(i,v){
        $(v).find('.articleNumberText').text('Articulo '+ (i+1));
        $(v).find('.articleNumber').val(i+1);
    });
}

function addRowAddress() {
    var newSC = defaultAddRow(articleGroup,numberOfArticleGroup());
    updateArticleNumber(newSC);
}

function deleteArticle(group){
    deleteGroup(group,numberOfArticleGroup());
    reLabelArticles();
}

function getLawId() {
    return $('#lawId').val();
}

function getVersionId() {
    return $('#versionId').val();
}

function editArticle(group) {
    var articleValue = $(group).find('textarea').val();
    var articleNumber = $(group).find('.articleNumber').val();

    var params = {
        lawId: getLawId(),
        articleNumber: articleNumber,
        articleValue: articleValue
    };

    $.post('/law/edit', params, function(data){addMachineToList(data); });
}