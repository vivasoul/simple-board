import {ref} from "vue"

const _title = ref("")
const _catNo = ref(1)
const _files = ref([])
const _content = ref("")

const defaultData = {
    title: "",
    catNo: -1,
    files: [],
    content: ""
}

const init = function(initData) {

    let _data;
    if(initData === null) {
        _data = defaultData;
    } else if(typeof initData === "object") {
        _data = initData;
    }

    if(typeof _data === "object") {
        const {title, catNo, files, content} = _data

        _title.value = title || defaultData.title;
        _catNo.value = catNo || defaultData.catNo;
        _files.value = files || defaultData.files;
        _content.value = content || defaultData.content;

    }
}

export default function useBoardDetail(initData) {

    init(initData)

    return {
        title: _title,
        catNo: _catNo,
        files: _files,
        content: _content,
        init
    }
}