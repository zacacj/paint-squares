fromCategory('Territory')
    .foreachStream().when(
    {
        $any: function (s, e) {
            linkTo("Territory", e);
        }
    });