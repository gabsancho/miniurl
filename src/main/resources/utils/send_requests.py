import requests
import uuid
import time
import argparse


def send_unique_requests(url: str, count: int):
    for i in range(count):
        unique_text = f"Unique text {uuid.uuid4()}"
        print(f"Sending request {i + 1} with body: {unique_text}")
        response = requests.post(url, data=unique_text, headers={"Content-Type": "text/plain"})
        print(f"Response {i + 1}: {response.status_code} - {response.text}\n")


if __name__ == "__main__":
    parser = argparse.ArgumentParser(description="Send unique POST requests to a URL")
    parser.add_argument("--count", type=int, default=5, help="Number of requests to send")
    parser.add_argument("--url", type=str, default="http://localhost:8080/register", help="Target URL")
    args = parser.parse_args()

    send_unique_requests(args.url, args.count)