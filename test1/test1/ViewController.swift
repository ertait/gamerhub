//
//  ViewController.swift
//  test1
//
//  Created by Emma Tait on 10/25/16.
//  Copyright © 2016 Emma Tait. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    

    @IBOutlet var loginScreen: UIView!
    @IBOutlet weak var password: UITextField!
    @IBOutlet weak var username: UITextField!
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


    @IBAction func login(sender: AnyObject) {
    }
}

