import {getTinymce} from "@tinymce/tinymce-vue/lib/es2015/main/ts/TinyMCE"

export default function useContentEditor() {

    return {
        insertImage({downFilePath}) {
            getTinymce().activeEditor.insertContent(`<img src="${downFilePath}" style="max-width:100%;object-fit: scale-down"/>`)
        }
    }
}