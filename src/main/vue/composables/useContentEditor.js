import {getTinymce} from "@tinymce/tinymce-vue/lib/es2015/main/ts/TinyMCE"

export default function useContentEditor() {

    return {
        insertImage({downPath}) {
            getTinymce().activeEditor.insertContent(`<img src="${downPath}" style="max-width:100%;object-fit: scale-down"/>`)
        },
        insertNewLine() {
            const editor = getTinymce().activeEditor
            editor.insertContent("<br/>")
            editor.focus(false)
        },
        focusLast() {
            const editor = getTinymce().activeEditor
            editor.selection.select(editor.getBody(), true)
            editor.selection.collapse(false)
            editor.focus()
        }
    }
}