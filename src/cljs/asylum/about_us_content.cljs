(ns asylum.about-us-content)

(def about-us
  {:title "About Us"
   :media {:name "about-us.jpg" :type :image}
   :content "<p>We are team of software engineers that work together in Sydney, Australia. We started using Clojure a few years ago and find it really useful for solving almost any problem.  We decided to make this app to raise awareness to this issue, and hope this has been a learning experience.  We're very happy to be part of the inaugural Clojure Cup event.  If you'd like to vote for us, please do so <a href='http://clojurecup.com/app.html?app=asylum'>here</a>.  If you'd like to get in touch, please email us at <a href='mailto:info@icm-consulting.com.au'>info@icm-consulting.com.au</a>.</p>"
   :links []
   :options {:dismiss {:effect (fn [] {}) :title "Thanks, now I know" :description ""}}})
