window._studentList;
var loadStudentList = function() {
    $.getJSON("/api/student", function(list) {
        var $dock = $('#student-dock');
        var tmpl = "<tr><td><a class='update' data-id='{0}'>{0}</a></td><td>{1}</td><td>{2}</td></tr>";
        $dock.html("");
        $.each(list, function(id, student) {
            $(tmpl.fmt(student.id, student.fullName, student.address.asString)).appendTo($dock);
        })
        window._studentList = list;
    })
}

var findStudent = function(id) {
    for (var i = 0, j = _studentList.length; i < j; ++i) {
        if (_studentList[i].id == id) {
            return _studentList[i];
        }
    }
    return false;
}

var updateStudent = function(id) {
    var student = _studentList
    $.post("/api/student/" + id, {student: findStudent(id)}, function(data) {
        console.log(data);
    });
}

$(function() {
    loadStudentList();
    $('body').on('click', 'a.update', function() {
        var id = $(this).data('id');
        updateStudent(id);
        return false;
    })
})
