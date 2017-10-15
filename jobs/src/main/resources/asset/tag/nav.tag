<nav>
    <div class='menu pointer {active: current === menu}' each='{menu in menuList}' onclick='{open}'>
        <br/>
        {menu.label}
    </div>
    <style>
        nav {
            font-size: 0.85em;
        }
        .menu.active {
            font-weight: bold;
        }
    </style>
    <script>
        var self = this
        self.menuList = [
        {label: 'Logs', view: 'log'},
        {label: 'Progress', view: 'progress'},
        {label: 'About', view: 'welcome'}
        ]
        self.current = self.menuList[2]
        open(e) {
            self.current = e.item.menu
            riot.store.trigger('open', {view: e.item.menu.view})
        }
    </script>
</nav>