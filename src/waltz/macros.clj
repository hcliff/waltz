(ns waltz.macros)

(defmacro defstate
  [sm name & body]
  `(let [s# (-> (waltz.state/state*)
                ~@body)]
     (waltz.state/add-state ~sm ~name s#)))

(defmacro defevent
  [sm name params & body]
  `(waltz.state/add-event ~sm ~name (fn ~params
                                      ~@body)))

(defmacro constraint [sm & body]
  (if (second body)
    `(waltz.state/constraint* ~sm (fn ~@body))
    `(waltz.state/constraint* ~sm ~@body)))

(defmacro in [sm & body]
  (if (second body)
    `(waltz.state/in* ~sm (fn ~@body))
    `(waltz.state/in* ~sm ~@body)))

(defmacro out [sm & body]
  (if (second body)
    `(waltz.state/out* ~sm (fn ~@body))
    `(waltz.state/out* ~sm ~@body)))