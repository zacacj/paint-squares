fromCategory('ErrorAggregateRoot')
    .foreachStream().when(
    {
        $any: function (s, e) {
            linkTo("ErrorAggregateRoot", e);
        }
    });