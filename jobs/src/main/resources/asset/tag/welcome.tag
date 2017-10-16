<welcome>
    <div id="doc-content"></div>
    <style>
        section {
            margin-top: 20px;
            position: relative;
            display: block
        }
        section .q {
            margin-bottom: 10px;
            font-weight: 600;
        }
        section ul {
            list-style: disc;
            padding-left: 20px;
        }
    </style>
    <script>
        $.get("https://raw.githubusercontent.com/actframework/act-demo-apps/master/jobs/README.md", function(result) {
            var md = markdownit({
                html: true,
                highlight: function (code, lang) {
                    if (lang && hljs.getLanguage(lang)) {
                        try {
                            return hljs.highlight(lang, code).value;
                        } catch (__) {}
                    } else {
                        console.log("lang not supported: " + lang)
                    }

                    return ''; // use external default escaping
                }
            }).use(markdownitFootnote);

            function setOutput(val) {
                var out = document.getElementById('doc-content');
                if (!out) return
                var old = out.cloneNode(true);
                out.innerHTML = md.render(val);

                var allold = old.getElementsByTagName("*");
                if (allold === undefined) return;

                var allnew = out.getElementsByTagName("*");
                if (allnew === undefined) return;

                for (var i = 0, max = Math.min(allold.length, allnew.length); i < max; i++) {
                    if (!allold[i].isEqualNode(allnew[i])) {
                        out.scrollTop = allnew[i].offsetTop;
                        return;
                    }
                }

                setTimeout(function() {
                    $('pre.line-numbers > code').each(function(i, block) {
                        hljs.lineNumbersBlock(block);
                    });
                })
            }

            setOutput(result);
        })
    </script>
</welcome>