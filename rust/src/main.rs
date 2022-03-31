use std::any::Any;

#[repr(usize)]
enum MyCar {
    Wheels,
    Seats,
    Manufacturer,
}

fn main() {
    let my_car: [Box<dyn Any>; 3] = [Box::new(4), Box::new(4), Box::new("X")];

    println!(
        "My car has {} wheels and {} seats, and it was made by {}.",
        my_car[MyCar::Wheels as usize]
            .downcast_ref::<i32>()
            .unwrap(),
        my_car[MyCar::Seats as usize].downcast_ref::<i32>().unwrap(),
        my_car[MyCar::Manufacturer as usize]
            .downcast_ref::<&'static str>()
            .unwrap()
    );
}