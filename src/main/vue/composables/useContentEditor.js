import {getTinymce} from "@tinymce/tinymce-vue/lib/es2015/main/ts/TinyMCE"

export default function useContentEditor() {

    return {
        insertImage({downPath}) {
            getTinymce().activeEditor.insertContent(`<img src="${downPath}" style="max-width:100%;object-fit: scale-down"/>`)
        },
        insertNewLine() {
            const editor = getTinymce().activeEditor
            editor.insertContent("<br/><br/>")
        },
        focusLast(domFocus) {
            const editor = getTinymce().activeEditor
            editor.selection.select(editor.getBody(), true)
            editor.selection.collapse(false)
            editor.focus()

            if(domFocus) {
                const dom = document.querySelector(".tox.tox-tinymce .tox-edit-area iframe")
                if(dom) {
                    dom.focus()
                }
                const win = editor.getWin()
                const scrollHeight = win.document.body.scrollHeight
                win.scrollTo(0, scrollHeight)
            }
        }
    }
}