import {getTinymce} from "@tinymce/tinymce-vue/lib/es2015/main/ts/TinyMCE"

export default function useContentEditor() {

    return {
        insertImage({downPath}) {
            getTinymce().activeEditor.insertContent(`<img src="${downPath}" style="max-width:100%;object-fit: scale-down"/>`)
        }
    }
}