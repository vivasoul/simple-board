import { mkdir, rmdir, cp } from 'node:fs'

export default function transportOutput({srcPath, destPath}) {
    return {
        name: "transport-output",
        closeBundle(err) {
            rmdir(destPath, function(){
                mkdir(destPath, {recursive:true}, (e) => {
                    if(e) throw e
                    cp(srcPath, destPath, {recursive: true},(e2) => { if(e2) console.log(e2) } )
                })
            })
            console.log("\nOutput is moved..")
        }
    }
}