const fn = {
    title: "hello world",
    tags: [1, 2, 3, 4],
    showTitle: () => {
        console.log(this.title);
    }
};

fn.showTitle();