// BAD_FIR_RESOLUTION
// IGNORE_BACKEND: JS_IR
// TODO: muted automatically, investigate should it be ran for JS or not
// DONT_RUN_GENERATED_CODE: JS
// IGNORE_BACKEND: JS

tailrec fun test(x : Int) : Int {
    return if (x == 1) {
        test(x - 1)
        1 + test(x - 1)
    } else if (x > 0) {
        test(x - 1)
    } else {
        0
    }
}

fun box() : String = if (test(1000000) == 1) "OK" else "FAIL"
