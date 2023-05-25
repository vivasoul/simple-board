import {ref} from "vue"
import { useRouter } from 'vue-router'

const _title = ref("")
const _catNo = ref(1)
const _files = ref([])
const _content = ref("")
const _passwd = ref("")

const defaultData = {
    title: "",
    catNo: -1,
    files: [],
    content: "",
    passwd: ""
}

const init = function(initData) {

    let _data;
    if(initData == null) {
        _data = defaultData;
    } else if(typeof initData === "object") {
        _data = initData;
    }

    if(typeof _data === "object") {
        const {title, catNo, files, content, passwd} = _data

        _title.value = title || defaultData.title
        _catNo.value = catNo || defaultData.catNo
        _files.value = files || defaultData.files
        _content.value = content || defaultData.content
        _passwd.value = passwd || defaultData.passwd

    }
}

export default function useBoardDetail(initData) {
    const router = useRouter()

    init(initData)

    return {
        title: _title,
        catNo: _catNo,
        files: _files,
        content: _content,
        passwd: _passwd,
        init,
        goToList() {
            if(history.state.back == null) {
                router.push("/")
            } else {
                router.go(-1)
            }
        }
    }
}